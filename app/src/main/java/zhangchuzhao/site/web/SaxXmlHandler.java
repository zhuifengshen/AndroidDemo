package zhangchuzhao.site.web;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import zhangchuzhao.site.skill.LogUtil;

import static zhangchuzhao.site.skill.BaseActivity.LOGTAG;

/**
 * Created by Devin on 2016/11/16.
 */

public class SaxXmlHandler extends DefaultHandler {

    private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;

    @Override
    public void startDocument() throws SAXException{
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        nodeName = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException{
        if ("id".equals(nodeName)){
            id.append(ch, start, length);
        }else if ("name".equals(nodeName)){
            name.append(ch, start, length);
        }else if("version".equals(nodeName)){
            version.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
        if ("app".equals(localName)){
            LogUtil.d(LOGTAG, "id: " + id.toString().trim());
            LogUtil.d(LOGTAG, "name: " + name.toString().trim());
            LogUtil.d(LOGTAG, "version: " + version.toString().trim());
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException{

    }
}
