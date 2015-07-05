package com.example.poli_mobile.poli_mobile.utilidades;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.poli_mobile_entidades.Noticia;

import android.content.Context;
import android.graphics.drawable.Drawable;


public class XMLRss {
	private URL url;

	public XMLRss(String url, Context context) {

		try {
			this.url = new URL(url);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Noticia> parse() {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();
		Noticia noticia;

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document dom = builder.parse(this.url.openConnection()
					.getInputStream());
			Element root = dom.getDocumentElement();
			NodeList items = root.getElementsByTagName("item");
			for (int i = 0; i < items.getLength(); i++) {
				noticia = new Noticia();
				Node item = items.item(i);
				NodeList properties = item.getChildNodes();
				for (int j = 0; j < properties.getLength(); j++) {
					Node property = properties.item(j);
					String name = property.getNodeName();

					if (name.equalsIgnoreCase("title")) {
						noticia.setTitulo(property.getFirstChild()
								.getNodeValue());
					}
					// else if (name.equalsIgnoreCase("content:encoded")){
					// int delimiter =
					// (property.getFirstChild().getNodeValue()).indexOf(" ] ");
					// noticia.setContenido(property.getFirstChild().getNodeValue().substring(delimiter+1));
					//
					// int startdelimiterImage =
					// property.getFirstChild().getNodeValue().indexOf("src=");
					// if(startdelimiterImage!=-1){
					//
					// String urlpart =
					// property.getFirstChild().getNodeValue().substring(startdelimiterImage+5);
					// int enddelimiterImage = urlpart.indexOf("\"");
					// noticia.setImage(property.getFirstChild().getNodeValue().substring(startdelimiterImage+5,startdelimiterImage+5+enddelimiterImage));
					// }
					//
					// }
					// else if (name.equalsIgnoreCase("enclosure")){
					// int delimiter =
					// (property.getFirstChild().getNodeValue()).indexOf(" ] ");
					// noticia.setContenido(property.getFirstChild().getNodeValue().substring(delimiter+1));
					//
					// int startdelimiterImage =
					// property.getFirstChild().getNodeValue().indexOf("src=");
					// if(startdelimiterImage!=-1){
					//
					// String urlpart =
					// property.getFirstChild().getNodeValue().substring(startdelimiterImage+5);
					// int enddelimiterImage = urlpart.indexOf("\"");
					// NamedNodeMap nmp =
					// property.getFirstChild().getAttributes();
					// noticia.setImage(property.getFirstChild().getAttributes().getNamedItem("url").getNodeValue());
					// }

//					 }
//					 else if (name.equalsIgnoreCase("image")){
//					
//					 NodeList propertiesImage = property.getChildNodes();
//					 for (int h=0;h<propertiesImage.getLength();h++){
//					 Node pImages = propertiesImage.item(h);
//					 String Imagename = pImages.getNodeName();
//					 if (name.equalsIgnoreCase("url")){
//					 noticia.setImage(pImages.getFirstChild().getNodeValue());
//					 }
//					
//					 }
//					 }

					else if (name.equalsIgnoreCase("description")) {

//						String splt1[] = property.getFirstChild()
//								.getNodeValue().toString().split("]]");
//						if(splt1.length>1){
//						String splt2[] = splt1[0].split("<p>");
//						String Description = splt2[1];
//
//						String a = property.getFirstChild().getNodeValue()
//								.replaceAll("\"", "'");
//
//						splt1 = a.split("' mce_src");
//						if (splt1.length >= 1) {
//							splt2 = splt1[0].split("src='");
//							if (splt2.length >= 2) {
//								String imageDescription = splt2[1];
//								try {
//									InputStream is = (InputStream) new URL(
//											imageDescription).getContent();
//									Drawable d = Drawable.createFromStream(is,
//											"description image");
//									noticia.setImage(d);
//
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//							}
//						}
//
//						noticia.setResumen(Description);
//						}
					}

					else

					if (name.equalsIgnoreCase("link")) {
						noticia.setEnlace(property.getFirstChild()
								.getNodeValue());
						// }else if(name.equalsIgnoreCase("pubDate")){
						// noticia.setFecha(formatter.parse(""+property.getFirstChild().getNodeValue()));

					}
				}
				noticias.add(noticia);
				// Log.i("Parsher", "notcia:" + i);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return noticias;
	}

}
