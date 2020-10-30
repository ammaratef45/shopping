package edu.miu.groupx.smtp.emailservice.service;


import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;




//    public void sentEmail(String emailAddress){
//    }

    public void sendEmail(String emailAddress) throws MessagingException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("user1.shopping@gmail.com");

        helper.setSubject("Confirm email for the order 122111");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html

        helper.setText("<table class=\"m_7440377928157732025container\" style=\"width:560px;text-align:left;border-spacing:0;border-collapse:collapse;margin:0 auto\">\n" +
                "          <tbody><tr>\n" +
                "            <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif\">\n" +
                "              \n" +
                "            <h2 style=\"font-weight:normal;font-size:24px;margin:0 0 10px\">Thank you for your purchase! </h2>\n" +
                "            <p style=\"color:#777;line-height:150%;font-size:16px;margin:0\">\n" +
                "  \n" +
                "    \n" +
                "      Hi Ngoc Vu, we're getting your order ready to be shipped. We will notify you when it has been sent.\n" +
                "    \n" +
                "  \n" +
                "</p>\n" +
                "            \n" +
                "              <table style=\"width:100%;border-spacing:0;border-collapse:collapse;margin-top:20px\">\n" +
                "  <tbody><tr>\n" +
                "    <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;line-height:0.5em\">&nbsp;</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif\">\n" +
                "      <table class=\"m_7440377928157732025button m_7440377928157732025main-action-cell\" style=\"border-spacing:0;border-collapse:collapse;float:left;margin-right:15px\">\n" +
                "        <tbody><tr>\n" +
                "          <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;border-radius:4px\" align=\"center\" bgcolor=\"#1990C6\"><a href=\"#\" class=\"m_7440377928157732025button__text\" style=\"font-size:16px;text-decoration:none;display:block;color:#fff;padding:20px 25px\" target=\"_blank\" data-saferedirecturl=\"\">View your order</a></td>\n" +
                "        </tr>\n" +
                "      </tbody></table>\n" +
                "      \n" +
                "    <table class=\"m_7440377928157732025secondary-action-cell\" style=\"border-spacing:0;border-collapse:collapse;margin-top:19px\">\n" +
                "      <tbody><tr>\n" +
                "        <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif\">or <a href=\" style=\"font-size:16px;text-decoration:none;color:#1990c6\" target=\"_blank\" data-saferedirecturl=\"\">Visit our store</a>\n" +
                "</td>\n" +
                "      </tr>\n" +
                "    </tbody></table>\n" +
                "\n" +
                "\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</tbody></table>\n" +
                "\n" +
                "            \n" +
                "\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>" +
                "<br><table style=\"width:100%;border-spacing:0;border-collapse:collapse;border-top-width:1px;border-top-color:#e5e5e5;border-top-style:solid\"> <tbody><tr> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;padding:40px 0\"> <center> <table class=\"m_7440377928157732025container\" style=\"width:560px;text-align:left;border-spacing:0;border-collapse:collapse;margin:0 auto\"> <tbody><tr> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif\"> <h3 style=\"font-weight:normal;font-size:20px;margin:0 0 25px\">Order summary</h3> </td> </tr> </tbody></table> <table class=\"m_7440377928157732025container\" style=\"width:560px;text-align:left;border-spacing:0;border-collapse:collapse;margin:0 auto\"> <tbody><tr> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif\"> <table style=\"width:100%;border-spacing:0;border-collapse:collapse\"> <tbody><tr style=\"width:100%\"> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;padding-bottom:15px\"> <table style=\"border-spacing:0;border-collapse:collapse\"> <tbody><tr><td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif\"> <img src=\"https://ci5.googleusercontent.com/proxy/AuFUE-YLLfSFabeLLlbxCJBCHC22DSyn9Ri7mjZx8ITz4u_9s2sBimONOn0YJefwHkTUjVshwfmUPa0sHsqzASo6HOhxTc02N9uPQaqwOcAeDyFEZbG63k13vrFe0NQetA47o-jEp6TeV96arloIUWOwGIxmjSGvLPMZpLpfOZxGxF-_9PI=s0-d-e1-ft#https://cdn.shopify.com/s/files/1/0277/2480/8266/products/product-image-1134066537_compact_cropped.jpg?v=1588223801\" align=\"left\" width=\"60\" height=\"60\" style=\"margin-right:15px;border-radius:8px;border:1px solid #e5e5e5\" class=\"CToWUd\"> </td> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;width:100%\"> <span style=\"font-size:16px;font-weight:600;line-height:1.4;color:#555\">Hammy - Large / Brown&nbsp;×&nbsp;1</span><br> <span style=\"font-size:14px;color:#999\"></span><br> <span style=\"font-size:14px;display:block;line-height:1.4;color:#555;margin:5px 0 0\"> <img src=\"https://ci5.googleusercontent.com/proxy/xP-Lmj5FIIkNj4eu1gbZ9kqsg3fwJpNjvcybd1nRYWWZFhptQjobFTsvbMI0nER5AZkLqyyswSDZbyNBcD8Ckd4SEDMD9jbQIk_fBLUwnOEcOesmdlhtr_etYubnnl_SKXDfNB1GGQMH9BEHZtFO29qd8A8ZFdPw6qMkg3n7xL0dZVrtfxHTTvChCh9rHQrLi_AzOm6N_O_5MHg9jnmXUFzB=s0-d-e1-ft#https://cdn.shopify.com/s/assets/themes_support/notifications/discounttag-d1f7c6d9334582b151797626a5ae244c56af0791fcd7841f21027dd44830bcc6.png\" width=\"18\" height=\"18\" style=\"vertical-align:middle;margin-right:6px\" class=\"CToWUd\"> <span style=\"font-size:14px;color:#999\"> STAY AT HOME SALE! (-$18.49) </span> </span> </td> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;white-space:nowrap\"> <del style=\"font-size:14px;display:block;text-align:right;color:#999\">$36.99</del> <p style=\"color:#555;line-height:150%;font-size:16px;font-weight:600;margin:0 0 0 15px\" align=\"right\"> $18.50 </p> </td> </tr></tbody></table> </td> </tr> <tr style=\"width:100%;border-top-width:1px;border-top-color:#e5e5e5;border-top-style:solid\"> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;padding-top:15px\"> <table style=\"border-spacing:0;border-collapse:collapse\"> <tbody><tr><td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif\"> <img src=\"https://ci5.googleusercontent.com/proxy/T7kf6WUnG5VdFXdvF2VmdX7-yFLEQ55aPh0JwLgg4Ir8R3Ev0SHXdPZQ-FlCK7YLVcz1YmhtuGIyfE-owuapywccyuHJAMyBBuZGcOLXodwSsRRYYu4tuFlcAuqjfwtVN7VVd2yWIMaMJhNmSBcE8YHtOAJpyP6vW_IG--2hn3THDdU_u9M=s0-d-e1-ft#https://cdn.shopify.com/s/files/1/0277/2480/8266/products/product-image-1183466422_compact_cropped.jpg?v=1574558237\" align=\"left\" width=\"60\" height=\"60\" style=\"margin-right:15px;border-radius:8px;border:1px solid #e5e5e5\" class=\"CToWUd\"> </td> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;width:100%\"> <span style=\"font-size:16px;font-weight:600;line-height:1.4;color:#555\">Geoff - Extra Large&nbsp;×&nbsp;1</span><br> <span style=\"font-size:14px;color:#999\"></span><br> </td> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;white-space:nowrap\"> <p style=\"color:#555;line-height:150%;font-size:16px;font-weight:600;margin:0 0 0 15px\" align=\"right\"> $64.99 </p> </td> </tr></tbody></table> </td> </tr></tbody></table> <table style=\"width:100%;border-spacing:0;border-collapse:collapse;margin-top:15px;border-top-width:1px;border-top-color:#e5e5e5;border-top-style:solid\"> <tbody><tr> <td class=\"m_7440377928157732025subtotal-spacer\" style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;width:40%\"></td> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif\"> <table style=\"width:100%;border-spacing:0;border-collapse:collapse;margin-top:20px\"> <tbody><tr> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;padding:5px 0\"> <p style=\"color:#777;line-height:1.2em;font-size:16px;margin:0\"> <span style=\"font-size:16px\">Subtotal</span> </p> </td> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;padding:5px 0\" align=\"right\"> <strong style=\"font-size:16px;color:#555\">$83.49</strong> </td></tr> <tr> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;padding:5px 0\"> <p style=\"color:#777;line-height:1.2em;font-size:16px;margin:0\"> <span style=\"font-size:16px\">Shipping</span> </p> </td> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;padding:5px 0\" align=\"right\"> <strong style=\"font-size:16px;color:#555\">$0.00</strong> </td></tr> <tr> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;padding:5px 0\"> <p style=\"color:#777;line-height:1.2em;font-size:16px;margin:0\"> <span style=\"font-size:16px\">Taxes</span> </p> </td> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;padding:5px 0\" align=\"right\"> <strong style=\"font-size:16px;color:#555\">$0.00</strong> </td></tr> </tbody></table> <table style=\"width:100%;border-spacing:0;border-collapse:collapse;margin-top:20px;border-top-width:2px;border-top-color:#e5e5e5;border-top-style:solid\"> <tbody><tr> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;padding:20px 0 0\"> <p style=\"color:#777;line-height:1.2em;font-size:16px;margin:0\"> <span style=\"font-size:16px\">Total</span> </p> </td> <td style=\"font-family:-apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,&quot;Roboto&quot;,&quot;Oxygen&quot;,&quot;Ubuntu&quot;,&quot;Cantarell&quot;,&quot;Fira Sans&quot;,&quot;Droid Sans&quot;,&quot;Helvetica Neue&quot;,sans-serif;padding:20px 0 0\" align=\"right\"> <strong style=\"font-size:24px;color:#555\">$83.49 USD</strong> </td></tr> </tbody></table> <p style=\"color:#777;line-height:1.1;font-size:16px;margin:10px 0 0\" align=\"right\"> You saved <span style=\"font-size:16px;color:#555\">$18.49</span> </p> </td> </tr></tbody></table> </td> </tr> </tbody></table> </center> </td> </tr></tbody></table>", true);

        //FileSystemResource file = new FileSystemResource(new File("classpath:android.png"));

        //Resource resource = new ClassPathResource("android.png");
        //InputStream input = resource.getInputStream();

        //ResourceUtils.getFile("classpath:android.png");

        // helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);

    }

}
