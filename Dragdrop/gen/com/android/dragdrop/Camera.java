package com.android.dragdrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;






public class Camera extends Activity implements OnClickListener{

	ImageView imageview,mImageView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.har);
	  //  Editoractivity e;
	//	e = new Editoractivity();
	//	setContentView(e);
		/*Intent i1 = new Intent(Menu.this,Leftright.class );   //("android.intent.action.Leftright");
	    Menu.this.startActivity(i1);
	    
	    Intent i = new Intent(Camera.this,Editoractivity.class);
	    Camera.this.startActivity(i);*/  //BACKUP CODE 
	    
		
	    Button btn = (Button) findViewById(R.id.button3);
	
	    btn.setOnClickListener(new View.OnClickListener() {

	        public void onClick(View v) {
	        	Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
	        		 startActivityForResult(intent, 0);
	       
	      	  
	        }
	    });
	    
	    Button btn1 = (Button) findViewById(R.id.button1);
	    btn1.setOnClickListener(new View.OnClickListener() {

	        @SuppressLint("SdCardPath")
			public void onClick(View v) {
	        	//Bitmap icon = mBitmap;
	        	imageview.buildDrawingCache();
	        	Bitmap icon = imageview.getDrawingCache();
	        //	Bitmap icon = BitmapFactory.decodeResource(getResources(), imageview.getId() );
	        	Intent share = new Intent(Intent.ACTION_SEND);
	        	share.setType("image/jpeg");
	        	ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	        	icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
	        	File f = new File(Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg");
	        	try {
	        	    f.createNewFile();
	        	    @SuppressWarnings("resource")
					FileOutputStream fo = new FileOutputStream(f);
	        	    fo.write(bytes.toByteArray());
	        	} catch (IOException e) {                       
	        	        e.printStackTrace();
	        	}
	        	share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/temporary_file.jpg"));
	        	startActivity(Intent.createChooser(share, "Share Image")); 
	        }
	    });
		 
		}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
		 // Button btn = (Button) findViewById(R.id.button3);
		   imageview =(ImageView) findViewById(R.id.imageView1);
	            Bitmap photo = (Bitmap) data.getExtras().get("data"); 
	            imageview.setImageBitmap(photo);
	            
	    ////save to sd card
	            
	            Bitmap bm;

	            View v= imageview;
	            v.setDrawingCacheEnabled(true);
	            bm=Bitmap.createBitmap(v.getDrawingCache());
	            v.setDrawingCacheEnabled(false);

	            String fileName="image.png";
	            File file=new File(fileName);

	            try 
	            {

	               FileOutputStream fOut=openFileOutput(fileName, MODE_PRIVATE);
	               bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);

	            }
	            catch (Exception e) 
	            {
	               e.printStackTrace();
	            }
	        
	    }
	  
	  
	
}
