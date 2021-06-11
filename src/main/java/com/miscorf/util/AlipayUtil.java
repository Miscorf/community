package com.miscorf.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.miscorf.pojo.Pay;

import java.util.*;


public class AlipayUtil {

    String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCRGarnkT5/us/g+OEYjGsWnn/EImlq8v0UNlQcOZpHcpaZ9QjJ3F8+2fj6pl8d5/PZh/meLudxJTl0odYMkbxg6YyUBYBLb42SweVZAX9G6kegaOFvQQIxhT0U82t3NyIzo+09tILSRdpwmK0apy5MF7JdC2vk9oLv4Zp2o8G5W9bKy1njFymFTJGDm24R3QNacB0tsMtA/rerUoIdDCZACA4xNXqRfIZaSwow+Rrkl59+pI6QTThpY4nBaR6o6syFgIB20c7WIpnrv4/lYlyq9BPgtsFDyKiFPDZucxaca+W5dfdcxgqbO8g++s7+K/LPxppqgltol1Q1vfgrREbtAgMBAAECggEAQ43fs9A7eNxQzD0TD4rQkMdaVMZwrgpTKYXhwwJOgdUnqKYcYV7oOSricE/lGNdVAIH+BVdxCYx7I4H1VgTD35rciE3HXVeiYiFD1hyMVUfd8SDjVBjAHK4bshVigy54DfLUEVZpUdyTxrhOX4pWU7b0o70djov2ihhPAc1b9B/HAK6ZNVD7h+WKhraIxniqBG6aRFH7S964QvNpp0FbPiLy3sGd1B90X2NDx9xzHGPTQgyRd0IKHiI+aHmii9AG5pHT544YwED+FzCfE/La8+Xg96aLl91MIIiHrvT3U4Qt2/rbydZhGwd03abr2IuoOLrGlUq/rtHZ+Xeb4bK/QQKBgQDqkv90AHdHo2Bt0c5jSP3A4aJPHL8iFgKz67wKzCEYDjkLGjm1y7PTEBD2N8Arl4GJRJw+dQlAnvUprcH/V3X+fwzccwVMnq/xqMHB9xaEuY5AUoHJZRvSU8y4RU9AKiqwuwQofz6lGM+ymcEButZov01Bge5xwTDJT9LyxUrCFwKBgQCeWoSqps0daV0mreYmVFZ2crDRzOolfoiIGOUpGPskwGOcHJWBM1k1PwVLUn3X3mmWpbI78CTySQ1Y3zsZXaaV/acaEb5GwtgGKaFkpYVO9Y1jFMnzL0e4Ab2NiSa9ANuS9lEfPLA/pC3uZ2Adi1v5/FABpoT4Vxxd91sz5v41mwKBgHmsagj3vOdR3VDZhR6G2+jSoOg8VwANPrWoz08idwANUJVrsweTc+FE8idk36881RoolornFjeIvE40LO6PpUqvCDLJDrShk03cZTSBQBL0VM8UfPHEGlxMz2G9wc/cj2xAdXW0GwB0EDfp/O0yxshqgl46UT58IRwq5Za7s31dAoGBAIBhSmU/zdMmdMrC+W/huCdXXFr7EmlLaE1OQZtMEW/+OcN6uQqCIrS0Xwpt2nCEdH4Z36IQUyPKbfO7JyKtdjzr4+mSsPyVQqXcYmhCeQ1GrlXBqOhrUG2xm4d/xdQ9Ocavw5zbithNtp8tWBMbxHmrQQHVir/7f5f+zgYKI9Y5AoGABxQ20wClkcMPp2CwW7QZ6P40hHgsPgm+aV+gajbX6YFRMYx4ZAQ7ocPBO/Upk84bgdFiZ3YtBaIG/M9Nixu5fj8LXKxVCRGyUvKOqjoyuPRQe8LD27muiEbjyG7eckJP9C923AvB+atbCHusWF7+EnQCntzps1X/By6X9YkcbP8=";
    String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2pxebOib+rIn2NZbr9zBT08W9GkFv8MwWgpzMBktrVJlZgQGpa27E5usz6nAjxm2zPNOZnUXkURUPykqfyNlAXR7iXTcruTR/vo4ayIeJhts1hYj/2Gl4DX2YjqBxDVuS8p7TqBNgBdgty9F/SHYYHNtzAmsENJhchVusAFYF0O8vY2TyfZHZLInyDRdKC/SvH4Y69FsfucWb6rvRUgDorPZ3RUwUP91KMXbiP8z6v092vz474Z4MjuzOO+oV084YAxXlUeA+xI9a4fzJFuIzDUQxE/Ev5NpC+CRmzgtpXIDnE6lKyRAvpI7t3yQ7GYjSlJ8/lyxxUUSTibDXL3H1wIDAQAB";
    AlipayClient alipayClient = new DefaultAlipayClient(
            "https://openapi.alipaydev.com/gateway.do",
            "2021000117652048",
            APP_PRIVATE_KEY,
            "json",
            "utf-8",
            ALIPAY_PUBLIC_KEY,
            "RSA2");

//    AlipayClient defaultClient(){
//        String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCRGarnkT5/us/g+OEYjGsWnn/EImlq8v0UNlQcOZpHcpaZ9QjJ3F8+2fj6pl8d5/PZh/meLudxJTl0odYMkbxg6YyUBYBLb42SweVZAX9G6kegaOFvQQIxhT0U82t3NyIzo+09tILSRdpwmK0apy5MF7JdC2vk9oLv4Zp2o8G5W9bKy1njFymFTJGDm24R3QNacB0tsMtA/rerUoIdDCZACA4xNXqRfIZaSwow+Rrkl59+pI6QTThpY4nBaR6o6syFgIB20c7WIpnrv4/lYlyq9BPgtsFDyKiFPDZucxaca+W5dfdcxgqbO8g++s7+K/LPxppqgltol1Q1vfgrREbtAgMBAAECggEAQ43fs9A7eNxQzD0TD4rQkMdaVMZwrgpTKYXhwwJOgdUnqKYcYV7oOSricE/lGNdVAIH+BVdxCYx7I4H1VgTD35rciE3HXVeiYiFD1hyMVUfd8SDjVBjAHK4bshVigy54DfLUEVZpUdyTxrhOX4pWU7b0o70djov2ihhPAc1b9B/HAK6ZNVD7h+WKhraIxniqBG6aRFH7S964QvNpp0FbPiLy3sGd1B90X2NDx9xzHGPTQgyRd0IKHiI+aHmii9AG5pHT544YwED+FzCfE/La8+Xg96aLl91MIIiHrvT3U4Qt2/rbydZhGwd03abr2IuoOLrGlUq/rtHZ+Xeb4bK/QQKBgQDqkv90AHdHo2Bt0c5jSP3A4aJPHL8iFgKz67wKzCEYDjkLGjm1y7PTEBD2N8Arl4GJRJw+dQlAnvUprcH/V3X+fwzccwVMnq/xqMHB9xaEuY5AUoHJZRvSU8y4RU9AKiqwuwQofz6lGM+ymcEButZov01Bge5xwTDJT9LyxUrCFwKBgQCeWoSqps0daV0mreYmVFZ2crDRzOolfoiIGOUpGPskwGOcHJWBM1k1PwVLUn3X3mmWpbI78CTySQ1Y3zsZXaaV/acaEb5GwtgGKaFkpYVO9Y1jFMnzL0e4Ab2NiSa9ANuS9lEfPLA/pC3uZ2Adi1v5/FABpoT4Vxxd91sz5v41mwKBgHmsagj3vOdR3VDZhR6G2+jSoOg8VwANPrWoz08idwANUJVrsweTc+FE8idk36881RoolornFjeIvE40LO6PpUqvCDLJDrShk03cZTSBQBL0VM8UfPHEGlxMz2G9wc/cj2xAdXW0GwB0EDfp/O0yxshqgl46UT58IRwq5Za7s31dAoGBAIBhSmU/zdMmdMrC+W/huCdXXFr7EmlLaE1OQZtMEW/+OcN6uQqCIrS0Xwpt2nCEdH4Z36IQUyPKbfO7JyKtdjzr4+mSsPyVQqXcYmhCeQ1GrlXBqOhrUG2xm4d/xdQ9Ocavw5zbithNtp8tWBMbxHmrQQHVir/7f5f+zgYKI9Y5AoGABxQ20wClkcMPp2CwW7QZ6P40hHgsPgm+aV+gajbX6YFRMYx4ZAQ7ocPBO/Upk84bgdFiZ3YtBaIG/M9Nixu5fj8LXKxVCRGyUvKOqjoyuPRQe8LD27muiEbjyG7eckJP9C923AvB+atbCHusWF7+EnQCntzps1X/By6X9YkcbP8=";
//        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2pxebOib+rIn2NZbr9zBT08W9GkFv8MwWgpzMBktrVJlZgQGpa27E5usz6nAjxm2zPNOZnUXkURUPykqfyNlAXR7iXTcruTR/vo4ayIeJhts1hYj/2Gl4DX2YjqBxDVuS8p7TqBNgBdgty9F/SHYYHNtzAmsENJhchVusAFYF0O8vY2TyfZHZLInyDRdKC/SvH4Y69FsfucWb6rvRUgDorPZ3RUwUP91KMXbiP8z6v092vz474Z4MjuzOO+oV084YAxXlUeA+xI9a4fzJFuIzDUQxE/Ev5NpC+CRmzgtpXIDnE6lKyRAvpI7t3yQ7GYjSlJ8/lyxxUUSTibDXL3H1wIDAQAB";
//        AlipayClient alipayClient = new DefaultAlipayClient(
//                "https://openapi.alipaydev.com/gateway.do",
//                "2021000117652048",
//                APP_PRIVATE_KEY,
//                "json",
//                "utf-8",
//                ALIPAY_PUBLIC_KEY,
//                "RSA2");
//        return alipayClient;
//    }

    public String testAliPay() throws AlipayApiException {

//        String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCRGarnkT5/us/g+OEYjGsWnn/EImlq8v0UNlQcOZpHcpaZ9QjJ3F8+2fj6pl8d5/PZh/meLudxJTl0odYMkbxg6YyUBYBLb42SweVZAX9G6kegaOFvQQIxhT0U82t3NyIzo+09tILSRdpwmK0apy5MF7JdC2vk9oLv4Zp2o8G5W9bKy1njFymFTJGDm24R3QNacB0tsMtA/rerUoIdDCZACA4xNXqRfIZaSwow+Rrkl59+pI6QTThpY4nBaR6o6syFgIB20c7WIpnrv4/lYlyq9BPgtsFDyKiFPDZucxaca+W5dfdcxgqbO8g++s7+K/LPxppqgltol1Q1vfgrREbtAgMBAAECggEAQ43fs9A7eNxQzD0TD4rQkMdaVMZwrgpTKYXhwwJOgdUnqKYcYV7oOSricE/lGNdVAIH+BVdxCYx7I4H1VgTD35rciE3HXVeiYiFD1hyMVUfd8SDjVBjAHK4bshVigy54DfLUEVZpUdyTxrhOX4pWU7b0o70djov2ihhPAc1b9B/HAK6ZNVD7h+WKhraIxniqBG6aRFH7S964QvNpp0FbPiLy3sGd1B90X2NDx9xzHGPTQgyRd0IKHiI+aHmii9AG5pHT544YwED+FzCfE/La8+Xg96aLl91MIIiHrvT3U4Qt2/rbydZhGwd03abr2IuoOLrGlUq/rtHZ+Xeb4bK/QQKBgQDqkv90AHdHo2Bt0c5jSP3A4aJPHL8iFgKz67wKzCEYDjkLGjm1y7PTEBD2N8Arl4GJRJw+dQlAnvUprcH/V3X+fwzccwVMnq/xqMHB9xaEuY5AUoHJZRvSU8y4RU9AKiqwuwQofz6lGM+ymcEButZov01Bge5xwTDJT9LyxUrCFwKBgQCeWoSqps0daV0mreYmVFZ2crDRzOolfoiIGOUpGPskwGOcHJWBM1k1PwVLUn3X3mmWpbI78CTySQ1Y3zsZXaaV/acaEb5GwtgGKaFkpYVO9Y1jFMnzL0e4Ab2NiSa9ANuS9lEfPLA/pC3uZ2Adi1v5/FABpoT4Vxxd91sz5v41mwKBgHmsagj3vOdR3VDZhR6G2+jSoOg8VwANPrWoz08idwANUJVrsweTc+FE8idk36881RoolornFjeIvE40LO6PpUqvCDLJDrShk03cZTSBQBL0VM8UfPHEGlxMz2G9wc/cj2xAdXW0GwB0EDfp/O0yxshqgl46UT58IRwq5Za7s31dAoGBAIBhSmU/zdMmdMrC+W/huCdXXFr7EmlLaE1OQZtMEW/+OcN6uQqCIrS0Xwpt2nCEdH4Z36IQUyPKbfO7JyKtdjzr4+mSsPyVQqXcYmhCeQ1GrlXBqOhrUG2xm4d/xdQ9Ocavw5zbithNtp8tWBMbxHmrQQHVir/7f5f+zgYKI9Y5AoGABxQ20wClkcMPp2CwW7QZ6P40hHgsPgm+aV+gajbX6YFRMYx4ZAQ7ocPBO/Upk84bgdFiZ3YtBaIG/M9Nixu5fj8LXKxVCRGyUvKOqjoyuPRQe8LD27muiEbjyG7eckJP9C923AvB+atbCHusWF7+EnQCntzps1X/By6X9YkcbP8=";
//        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2pxebOib+rIn2NZbr9zBT08W9GkFv8MwWgpzMBktrVJlZgQGpa27E5usz6nAjxm2zPNOZnUXkURUPykqfyNlAXR7iXTcruTR/vo4ayIeJhts1hYj/2Gl4DX2YjqBxDVuS8p7TqBNgBdgty9F/SHYYHNtzAmsENJhchVusAFYF0O8vY2TyfZHZLInyDRdKC/SvH4Y69FsfucWb6rvRUgDorPZ3RUwUP91KMXbiP8z6v092vz474Z4MjuzOO+oV084YAxXlUeA+xI9a4fzJFuIzDUQxE/Ev5NpC+CRmzgtpXIDnE6lKyRAvpI7t3yQ7GYjSlJ8/lyxxUUSTibDXL3H1wIDAQAB";
//        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
//                "2021000117652048", APP_PRIVATE_KEY, "json", "utf-8", ALIPAY_PUBLIC_KEY, "RSA2");
//

        String total_amount = "100";
        String out_trade_no =UUID.randomUUID()+"";
        //订单名称，必填
        String subject = "北京烤鸭";
        String body = "北京烤鸭";
        //商品描述，可空
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c-";
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+ out_trade_no +"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+ total_amount +"," +
                "    \"subject\":\""+ subject +"\"," +
                "    \"body\":\""+ body +"\"" +
                "    }"+
                "  }");

        //请求
        alipayRequest.setReturnUrl("http://localhost:9528/#/dashboard");
        String form = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println(form);
        return  form;

    }

    public String payAliPay(Pay pay) throws AlipayApiException {

//        String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCRGarnkT5/us/g+OEYjGsWnn/EImlq8v0UNlQcOZpHcpaZ9QjJ3F8+2fj6pl8d5/PZh/meLudxJTl0odYMkbxg6YyUBYBLb42SweVZAX9G6kegaOFvQQIxhT0U82t3NyIzo+09tILSRdpwmK0apy5MF7JdC2vk9oLv4Zp2o8G5W9bKy1njFymFTJGDm24R3QNacB0tsMtA/rerUoIdDCZACA4xNXqRfIZaSwow+Rrkl59+pI6QTThpY4nBaR6o6syFgIB20c7WIpnrv4/lYlyq9BPgtsFDyKiFPDZucxaca+W5dfdcxgqbO8g++s7+K/LPxppqgltol1Q1vfgrREbtAgMBAAECggEAQ43fs9A7eNxQzD0TD4rQkMdaVMZwrgpTKYXhwwJOgdUnqKYcYV7oOSricE/lGNdVAIH+BVdxCYx7I4H1VgTD35rciE3HXVeiYiFD1hyMVUfd8SDjVBjAHK4bshVigy54DfLUEVZpUdyTxrhOX4pWU7b0o70djov2ihhPAc1b9B/HAK6ZNVD7h+WKhraIxniqBG6aRFH7S964QvNpp0FbPiLy3sGd1B90X2NDx9xzHGPTQgyRd0IKHiI+aHmii9AG5pHT544YwED+FzCfE/La8+Xg96aLl91MIIiHrvT3U4Qt2/rbydZhGwd03abr2IuoOLrGlUq/rtHZ+Xeb4bK/QQKBgQDqkv90AHdHo2Bt0c5jSP3A4aJPHL8iFgKz67wKzCEYDjkLGjm1y7PTEBD2N8Arl4GJRJw+dQlAnvUprcH/V3X+fwzccwVMnq/xqMHB9xaEuY5AUoHJZRvSU8y4RU9AKiqwuwQofz6lGM+ymcEButZov01Bge5xwTDJT9LyxUrCFwKBgQCeWoSqps0daV0mreYmVFZ2crDRzOolfoiIGOUpGPskwGOcHJWBM1k1PwVLUn3X3mmWpbI78CTySQ1Y3zsZXaaV/acaEb5GwtgGKaFkpYVO9Y1jFMnzL0e4Ab2NiSa9ANuS9lEfPLA/pC3uZ2Adi1v5/FABpoT4Vxxd91sz5v41mwKBgHmsagj3vOdR3VDZhR6G2+jSoOg8VwANPrWoz08idwANUJVrsweTc+FE8idk36881RoolornFjeIvE40LO6PpUqvCDLJDrShk03cZTSBQBL0VM8UfPHEGlxMz2G9wc/cj2xAdXW0GwB0EDfp/O0yxshqgl46UT58IRwq5Za7s31dAoGBAIBhSmU/zdMmdMrC+W/huCdXXFr7EmlLaE1OQZtMEW/+OcN6uQqCIrS0Xwpt2nCEdH4Z36IQUyPKbfO7JyKtdjzr4+mSsPyVQqXcYmhCeQ1GrlXBqOhrUG2xm4d/xdQ9Ocavw5zbithNtp8tWBMbxHmrQQHVir/7f5f+zgYKI9Y5AoGABxQ20wClkcMPp2CwW7QZ6P40hHgsPgm+aV+gajbX6YFRMYx4ZAQ7ocPBO/Upk84bgdFiZ3YtBaIG/M9Nixu5fj8LXKxVCRGyUvKOqjoyuPRQe8LD27muiEbjyG7eckJP9C923AvB+atbCHusWF7+EnQCntzps1X/By6X9YkcbP8=";
//        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2pxebOib+rIn2NZbr9zBT08W9GkFv8MwWgpzMBktrVJlZgQGpa27E5usz6nAjxm2zPNOZnUXkURUPykqfyNlAXR7iXTcruTR/vo4ayIeJhts1hYj/2Gl4DX2YjqBxDVuS8p7TqBNgBdgty9F/SHYYHNtzAmsENJhchVusAFYF0O8vY2TyfZHZLInyDRdKC/SvH4Y69FsfucWb6rvRUgDorPZ3RUwUP91KMXbiP8z6v092vz474Z4MjuzOO+oV084YAxXlUeA+xI9a4fzJFuIzDUQxE/Ev5NpC+CRmzgtpXIDnE6lKyRAvpI7t3yQ7GYjSlJ8/lyxxUUSTibDXL3H1wIDAQAB";
//        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
//                "2021000117652048", APP_PRIVATE_KEY, "json", "utf-8", ALIPAY_PUBLIC_KEY, "RSA2");
//

        String total_amount = pay.getPay_money()+"";
        System.out.println("!!!!"+total_amount);
        String out_trade_no =UUID.randomUUID()+"";
        //订单名称，必填
        String subject = pay.getPay_title();
        String body = pay.getPay_content();
        //商品描述，可空
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c-";
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+ out_trade_no +"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+ total_amount +"," +
                "    \"subject\":\""+ subject +"\"," +
                "    \"body\":\""+ body +"\"" +
                "    }"+
                "  }");
        alipayRequest.setReturnUrl("http://localhost:9528/#/dashboard");
        alipayRequest.setNotifyUrl("http://127.0.0.1:8080/pay/checkPay");
        //请求
        String form = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println(form);
        return  form;
    }



}
