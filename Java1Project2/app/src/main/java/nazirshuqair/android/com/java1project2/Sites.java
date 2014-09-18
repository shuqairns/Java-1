package nazirshuqair.android.com.java1project2;

/**
 * Created by nazirshuqair on 9/18/14.
 */
public class Sites {

    public static Sites newInstance(String _name,
                                    String _url,
                                    int _siteImage){

        Sites site = new Sites();
        site.setmName(_name);
        site.setmUrl(_url);
        site.setmImage(_siteImage);
        return site;

    }

    private String mName;
    private String mUrl;
    private int mImage;

    public Sites(){

        mImage = 0;
        mName = mUrl = "";
    }

    public Sites(String _name, String _url, int _siteImage){
        mName = _name;
        mUrl = _url;
        mImage = _siteImage;
    }

    public int getmImage(){
        return mImage;
    }

    public void setmImage(int _image){
        mImage = _image;
    }

    public String getmName(){
        return mName;
    }

    public void setmName(String _name){
        mName = _name;
    }

    public String getmUrl(){
        return mUrl;
    }

    public void setmUrl(String _url){
        mUrl = _url;
    }

}
