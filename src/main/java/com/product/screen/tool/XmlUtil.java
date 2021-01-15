package com.product.screen.tool;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component
public class XmlUtil {

    public String convertToXmlString(Object target, String encoding){
        return (String)convertToXml(target, "String", encoding);
    }

    public File convertToXmlFile(Object target, String encoding, String fileUrl){
        File file = new File(fileUrl);
        if(file.exists()){
            System.out.println("文件已存在");
        } else {
            try {
                file.createNewFile();
            }catch(IOException e){e.printStackTrace();}
        }
        File result = (File)convertToXml(target, file, encoding);
        return result;
    }

    private Object convertToXml(Object target, Object type, String encoding) {
        Object result = null;
        try{
            JAXBContext context = JAXBContext.newInstance(target.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            result = marshalToType(target, type, marshaller);
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    private Object marshalToType(Object target, Object type, Marshaller marshaller) throws Exception {
        Object result = null;
        if(type instanceof String){
            StringWriter writer = new StringWriter();
            marshaller.marshal(target, writer);
            // remove the standalone="yes" in the xml header
            result = writer.toString().replace("standalone=\"yes\"", "");
        } else if(type instanceof File){
            marshaller.marshal(target, (File)type);
            result = type;
        } else {
            throw new Exception("unknown target type");
        }
        return result;
    }

    public <T> T convertStringToObject(String xml, Class<T> clazz){
        return convertToObject(xml, clazz);
    }

    public <T> T convertFileToObject(File file, Class<T> clazz){
        return convertToObject(file, clazz);
    }

    private <T> T convertToObject(Object xml, Class<T> clazz){
        T clazz_ = null;
        try{
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            clazz_ = unmarshalByType(xml, unmarshaller);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clazz_;
    }

    private <T> T unmarshalByType(Object xml, Unmarshaller unmarshaller) throws Exception {
        T clazz_ = null;
        if(xml instanceof String)
            clazz_ = (T)unmarshaller.unmarshal(new StringReader((String)xml));
        else if(xml instanceof File)
            clazz_ = (T)unmarshaller.unmarshal((File) xml);
        else
            throw new Exception("unknown xml type");

        return clazz_;
    }
}
