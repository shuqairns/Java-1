package nazirshuqair.android.com.java1project2;

/**
 * Created by nazirshuqair on 9/18/14.
 */
public class Sites {

    public static Sites newInstance(String _name,
                                    String _url,
                                    int _siteImage){

        Sites site = new Sites();
        site.setsName(_name);
        site.setsUrl(_url);
        site.setsImage(_siteImage);
        return site;

    }

    private String sName;
    private String sUrl;
    private int sImage;

    public Sites(){

        sImage = 0;
        sName = sUrl = "";
    }

    public Sites(String _name, String _url, int _siteImage){
        sName = _name;
        sUrl = _url;
        sImage = _siteImage;
    }

    public int getsImage(){
        return sImage;
    }

    public void setsImage(int _image){
        sImage = _image;
    }

    public String getsName(){
        return sName;
    }

    public void setsName(String _name){
        sName = _name;
    }

    public String getsUrl(){
        return sUrl;
    }

    public void setsUrl(String _url){
        sUrl = _url;
    }

}
