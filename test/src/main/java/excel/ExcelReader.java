package excel;

import org.apache.http.client.utils.DateUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @date 2018/7/27 16:41
 */
public class ExcelReader {
    Workbook wb = null;

    public ExcelReader(String path) {
        try {
            InputStream inp = new FileInputStream(path);
            this.wb = WorkbookFactory.create(inp);
        } catch (FileNotFoundException var3) {
            var3.printStackTrace();
        } catch (InvalidFormatException var4) {
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    public ExcelReader(InputStream is) {
        try {
            this.wb = WorkbookFactory.create(is);
        } catch (FileNotFoundException var3) {
            var3.printStackTrace();
        } catch (InvalidFormatException var4) {
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    public List<String[]> getDataByIndex(int sheetIndex) {
        List<String[]> dataList = new ArrayList(100);
        int columnNum = 0;
        Sheet sheet = this.wb.getSheetAt(sheetIndex);
        if (sheet.getRow(0) != null) {
            columnNum = sheet.getRow(0).getLastCellNum() - sheet.getRow(0).getFirstCellNum();
        }

        if (columnNum > 0) {
            Iterator var5 = sheet.iterator();

            while(var5.hasNext()) {
                Row row = (Row)var5.next();
                String[] singleRow = new String[columnNum];
                int n = 0;

                for(int i = 0; i < columnNum; ++i) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    switch(cell.getCellType()) {
                        case 0:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                singleRow[n] = DateUtils.formatDate(cell.getDateCellValue(),"yyyyMMdd HH:mm:ss");
                            } else {
                                cell.setCellType(1);
                                String temp = cell.getStringCellValue();
                                if (temp.indexOf(".") > -1) {
                                    singleRow[n] = String.valueOf(new Double(temp)).trim();
                                } else {
                                    singleRow[n] = temp.trim();
                                }
                            }
                            break;
                        case 1:
                            singleRow[n] = cell.getStringCellValue().trim();
                            break;
                        case 2:
                            cell.setCellType(1);
                            singleRow[n] = cell.getStringCellValue();
                            if (singleRow[n] != null) {
                                singleRow[n] = singleRow[n].replaceAll("#N/A", "").trim();
                            }
                            break;
                        case 3:
                            singleRow[n] = "";
                            break;
                        case 4:
                            singleRow[n] = Boolean.toString(cell.getBooleanCellValue());
                            break;
                        case 5:
                            singleRow[n] = "";
                            break;
                        default:
                            singleRow[n] = "";
                    }

                    ++n;
                }

                dataList.add(singleRow);
            }
        }

        return dataList;
    }

    public int getRowNum(int sheetIndex) {
        Sheet sheet = this.wb.getSheetAt(sheetIndex);
        return sheet.getLastRowNum();
    }

    public int getColumnNum(int sheetIndex) {
        Sheet sheet = this.wb.getSheetAt(sheetIndex);
        Row row = sheet.getRow(0);
        return row != null && row.getLastCellNum() > 0 ? row.getLastCellNum() : 0;
    }

    public ExcelSheetModel getModelByIndex(int sheetIndex) {
        ExcelSheetModel model = new ExcelSheetModel();
        List<Map<String, Object>> paramMapList = new ArrayList();
        List<String[]> list = this.getDataByIndex(sheetIndex);
        if (list.size() > 1) {
            String[] headerArr = (String[])list.get(0);

            for(int i = 1; i < list.size(); ++i) {
                paramMapList.add(this.constructParamMap(headerArr, (String[])list.get(i)));
            }

            model.setSheetName(this.wb.getSheetName(sheetIndex));
            model.setResListMap(paramMapList);
            model.setHeaderList(Arrays.asList(headerArr));
        }

        return model;
    }

    public List<ExcelSheetModel> getModelList(int sheetNum) {
        List<ExcelSheetModel> modelList = new ArrayList();

        for(int i = 0; i < sheetNum; ++i) {
            modelList.add(this.getModelByIndex(i));
        }

        return modelList;
    }

    public Map<String, Object> constructParamMap(String[] headerArr, String[] dataArr) {
        Map<String, Object> paramMap = new HashMap();

        for(int i = 0; i < headerArr.length; ++i) {
            paramMap.put(headerArr[i], dataArr[i]);
        }

        return paramMap;
    }
}

