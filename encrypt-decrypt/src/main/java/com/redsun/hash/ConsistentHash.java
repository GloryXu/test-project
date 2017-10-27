package com.redsun.hash;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 一致性hash
 */
public class ConsistentHash {
    private int scope = 10000;
    private List<Node> nodes;

    public ConsistentHash() {
        nodes = new ArrayList<Node>();
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public void addNode(String nodeName) {
        if (nodeName == null || nodeName.trim().equals("")) {
            throw new IllegalArgumentException("name can't be null or empty");
        }

        if (containNodeName(nodeName)) {
            throw new IllegalArgumentException("duplicate name");
        }

        Node node = new Node(nodeName);
        if (nodes.size() == 0) {
            node.setStart(0);
            node.setEnd(scope);
            nodes.add(node);
        } else {
            Node maxNode = getMaxSectionNode();
            int middle = maxNode.start + (maxNode.end - maxNode.start) / 2;

            node.start = middle;
            node.end = maxNode.end;
            int maxPosition = nodes.indexOf(maxNode);
            nodes.add(maxPosition + 1, node);

            maxNode.setEnd(middle);

            // move data
            Iterator<String> iter = maxNode.datas.iterator();
            while (iter.hasNext()) {
                String data = iter.next();
                int value = Math.abs(data.hashCode()) % scope;
                if (value >= middle) {
                    iter.remove();
                    node.datas.add(data);
                }
            }
            for (String data : maxNode.datas) {
                int value = Math.abs(data.hashCode()) % scope;
                if (value >= middle) {
                    maxNode.datas.remove(data);
                    node.datas.add(data);
                }
            }
        }
    }

    public void removeNode(String nodeName) {
        if (!containNodeName(nodeName)) {
            throw new IllegalArgumentException("unknown name");
        }

        if (nodes.size() == 1 && nodes.get(0).datas.size() > 0) {
            throw new IllegalArgumentException("last node, and still have data");
        }

        Node node = findNode(nodeName);
        int position = nodes.indexOf(node);
        if (position == 0) {
            if (nodes.size() > 1) {
                Node newFirstNode = nodes.get(1);
                for (String data : node.datas) {
                    newFirstNode.datas.add(data);
                }
                newFirstNode.setStart(0);
            }
        } else {
            Node lastNode = nodes.get(position - 1);
            for (String data : node.datas) {
                lastNode.datas.add(data);
            }
            lastNode.setEnd(node.end);
        }
        nodes.remove(position);
    }

    public void addItem(String item) {
        if (item == null || item.trim().equals("")) {
            throw new IllegalArgumentException("item can't be null or empty");
        }

        int value = Math.abs(item.hashCode()) % scope;
        Node node = findNode(value);
        node.datas.add(item);
    }

    public void desc() {
        System.out.println("Status:");
        for (Node node : nodes) {
            System.out.println(node.name + ":(" + node.start + "," + node.end
                    + "): " + listString(node.datas));
        }
    }

    private String listString(LinkedList<String> datas) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        Iterator<String> iter = datas.iterator();
        if (iter.hasNext()) {
            buffer.append(iter.next());
        }

        while (iter.hasNext()) {
            buffer.append(", " + iter.next());
        }
        buffer.append("}");
        return buffer.toString();
    }

    private boolean containNodeName(String nodeName) {
        if (nodes.isEmpty()) {
            return false;
        }

        Iterator<Node> iter = nodes.iterator();
        while (iter.hasNext()) {
            Node node = iter.next();
            if (node.name.equals(nodeName)) {
                return true;
            }
        }

        return false;
    }

    private Node findNode(int value) {
        Iterator<Node> iter = nodes.iterator();
        while (iter.hasNext()) {
            Node node = iter.next();
            if (value >= node.start && value < node.end) {
                return node;
            }
        }

        return null;
    }

    private Node findNode(String nodeName) {
        Iterator<Node> iter = nodes.iterator();
        while (iter.hasNext()) {
            Node node = iter.next();
            if (node.name.equals(nodeName)) {
                return node;
            }
        }

        return null;
    }

    private Node getMaxSectionNode() {
        if (nodes.size() == 1) {
            return nodes.get(0);
        }

        Iterator<Node> iter = nodes.iterator();
        int maxSection = 0;
        Node maxNode = null;
        while (iter.hasNext()) {
            Node node = iter.next();
            int section = node.end - node.start;
            if (section > maxSection) {
                maxNode = node;
                maxSection = section;
            }
        }

        return maxNode;
    }

    static class Node {
        private String name;
        private int start;
        private int end;
        private LinkedList<String> datas;

        public Node(String name) {
            this.name = name;
            datas = new LinkedList<String>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public LinkedList<String> getDatas() {
            return datas;
        }

        public void setDatas(LinkedList<String> datas) {
            this.datas = datas;
        }
    }

    public static void main(String[] args) {
        ConsistentHash hash = new ConsistentHash();
        hash.addNode("Machine-1");
        hash.addNode("Machine-2");
        hash.addNode("Machine-3");
        hash.addNode("Machine-4");

        hash.addItem("Hello");
        hash.addItem("hash");
        hash.addItem("main");
        hash.addItem("args");
        hash.addItem("LinkedList");
        hash.addItem("end");

        hash.desc();

        hash.removeNode("Machine-1");

        hash.desc();

        hash.addNode("Machine-5");

        hash.desc();

        hash.addItem("scheduling");
        hash.addItem("queue");
        hash.addItem("thumb");
        hash.addItem("quantum");
        hash.addItem("approaches");
        hash.addItem("migration");
        hash.addItem("null");
        hash.addItem("feedback");
        hash.addItem("ageing");
        hash.addItem("bursts");
        hash.addItem("shorter");

        hash.desc();

        hash.addNode("Machine-6");
        hash.addNode("Machine-7");
        hash.addNode("Machine-8");

        hash.desc();

        hash.addNode("Machine-9");
        hash.addNode("Machine-10");
        hash.addNode("Machine-11");

        hash.desc();

        hash.addNode("Machine-12");
        hash.addNode("Machine-13");
        hash.addNode("Machine-14");
        hash.addNode("Machine-15");
        hash.addNode("Machine-16");
        hash.addNode("Machine-17");

        hash.desc();
    }

}