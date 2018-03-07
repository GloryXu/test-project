package message;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class PostNoArgs {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClients.createDefault();

        HttpPost post = new HttpPost("http://192.168.13.184:8080/demo/xxxx/1111/1111" );

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root xmlns=\"namespace_string\"><MsgHeader><SndDt>2018-01-15T14:14:19</SndDt><MsgTp>epcc.213.001.01</MsgTp><IssrId>G4000311000018</IssrId><Drctn>21</Drctn><SignSN>4000068829</SignSN><NcrptnSN>4002194153</NcrptnSN><DgtlEnvlp>EsC+FnsdROVq9/5nq6aIO4WoUmdtmoXywTMaBCDVpXODxi03aWbUgwhVXLhM8nRClz+De4Vscfh9HdUr4ixEWzvB9XsRSHaA3CkMUhLSPi6u1LMHVsfEYZ+Y1KEHhgOj0DWYXX9uKUjarjXTZMj38KD1CB6lFAUSFIqLWkwqJ69P11546fBQ/kpFKziWIfgYFOYEzhKpbr8yDJSKCoMNOwewGzMzEShU8iSHDZJZ3q73fC5CICRxPea4gQRjd/0eItbeQRHlnijtHPs/xboso8Tn+mLWxzpD6G6twRPzoI4mFR0BU7ISVagsiOi0nTt5aCaFIY2JkjnRhuNgMCELfQ==</DgtlEnvlp></MsgHeader><MsgBody><PyerAcctInf><PyerBkId>C1010611003601</PyerBkId><PyerBkNo>6222000000000000000</PyerBkNo><PyerBkNm>1234567890123456789</PyerBkNm></PyerAcctInf><PyerInf><PyerAcctIssrId>Z2006031000016</PyerAcctIssrId><PyerAcctTp>06</PyerAcctTp><PyerAcctNo>yYAPZb0uye5p/iBEm2ez6w==</PyerAcctNo><PyerAcctNm>MJgDycVhyk6wIoVZtEiZ0A==</PyerAcctNm><PyerMrchntNo></PyerMrchntNo><PyerMrchntNm></PyerMrchntNm><PyerMrchntShrtNm>211-001</PyerMrchntShrtNm></PyerInf><PyeeAcctInf><PyeeAcctTp>00</PyeeAcctTp><PyeeBkId>C0000000000088</PyeeBkId><PyeeBkNo>9EQ2fecJnsfEDMJX1EdT5w==</PyeeBkNo><PyeeSgnNo></PyeeSgnNo><PyeeBkNm>7Ts2jSZ8NIJskLT1UV4gLA==</PyeeBkNm></PyeeAcctInf><TrxInf><RPFlg>2</RPFlg><TrxCtgy>0120</TrxCtgy><BizTp>120007</BizTp><TrxId>2018011530001006351006351760107</TrxId><TrxDtTm>2017-10-11T14:52:30</TrxDtTm><TrxAmt>CNY201.00</TrxAmt><BatchId>B201710110015</BatchId><TrxSmmry></TrxSmmry><TrxPrps>0001</TrxPrps><TrxTrmTp>02</TrxTrmTp><TrxTrmNo></TrxTrmNo><TrxDevcInf></TrxDevcInf></TrxInf><SysRtnInf><SysRtnCd>00000000</SysRtnCd><SysRtnDesc>成功</SysRtnDesc><SysRtnTm>2017-10-11T15:02:52</SysRtnTm></SysRtnInf><BizInf><BizStsCd></BizStsCd><BizStsDesc></BizStsDesc><TrxFinishTm>2017-10-11T15:02:51</TrxFinishTm><TrxStatus>00</TrxStatus></BizInf></MsgBody></root>\\r\\n{S:bAPiC1syiVlLWvLiG91k1vH/FU03Juj/doG3V2iU72fA/n5RmRNS3P3x9qqwdmwkopTCqdxSBty5mqQosp9DuW7GgbzXOj8+wTVxCifK3zxS2NqrerUJUcXQyexRNPf3A7/k+4o5q++MiUvRSmADDJLYGSrZnWpEcymxMaF6kAEBJsIEFFE32CgUca8wHdhoZmKsnX0VKwlcFqKBCKpfdSE3oqZc1NxKUSnYpSVDRIHVb/ucTDly9/QjGQ/O/6fzG/KXDAN4DzfAsALE9/4dFFUD+0vUScaTe6UwNYMGOw1kdY1kRem8Qc3ZP8KWcxi5f5wcvw/oM8h6KdZwZvwK+A==}";

        StringEntity entity = new StringEntity(xml);
        entity.setContentEncoding( "UTF-8" );
//        entity.setContentType( "application/xml;charset=utf-8" );

        post.setEntity(entity);

        HttpResponse response = client.execute(post);

        HttpEntity resEntity = response.getEntity();
        String res = EntityUtils. toString(resEntity);
        Header[] headers = response.getHeaders("Content-Type");
        for (Header header : headers) {
            System.out.println("header name:" + header.getName() + ", header value:" + header.getValue());
        }
        System. out .println(res);
    }
}
