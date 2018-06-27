package com.mycompany.PostoAtendimento;

import android.app.*;
import android.os.*;
import android.util.*;
import android.widget.*;
import com.github.kevinsawicki.http.*;
import com.github.kevinsawicki.http.HttpRequest.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;


public class MainActivity extends Activity 
{
	
	private TextView textView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	
		textView = (TextView) findViewById(R.id.txt_user);
		
		
		
	//	try
	//	{
			//fazGet();
		String url = "https://olinda.bcb.gov.br/olinda/servico/Informes_PostosDeAtendimentoEletronico/versao/v1/swagger-ui3#/";
		String url2 = "https://olinda.bcb.gov.br/olinda/servico/Informes_PostosDeAtendimentoEletronico/versao/v1/odata/PostosAtendimentoEletronico";
		
		new DownloadTask().execute(url2);
	//	}
		/*catch (IOException e)
		{
			System.out.println(e.getMessage());
		}*/
    }
	
	public void fazGet() throws IOException
	{
		String url = "http://example.com"; 
		String charset = "UTF-8";
		
		
		URLConnection connection = new URL(url).openConnection(); 
		connection.setRequestProperty("Accept-Charset", charset); 
		
		try{
		InputStream response = connection.getInputStream(); 
		
		//try (Scanner scanner = new Scanner(response)) 
		//{ 
		
		Scanner scanner = new Scanner(response);
		String responseBody = scanner.useDelimiter("\\A").next(); 
		System.out.println(responseBody);
		}
		catch (IOException ioe)
		{
			
			System.out.println(ioe);
		}
		
	}
	
	private class DownloadTask extends AsyncTask<String, Void, String> 
	{ 
		protected String doInBackground(String... urls) 
		{ 
			
			String result = null;
			try 
			{ 
				HttpRequest request = HttpRequest.get(urls[0]);
				//File file = null;
				if (request.ok()) 
				{ 
				/*	try
					{
						file = File.createTempFile("download", ".tmp");
					}
					catch (IOException e)
					{}

					*/
					
					result = request.body();
					
					//textView.setText(result);

					//request.receive(file); 

					publishProgress(); 

				}
				else
					return null;
					
				return result; 
				
			} catch (HttpRequestException exception) 
			{ 
				return null; 
			} 
		} 
		protected void onProgressUpdate() 
		{ 
			//Log.d("MyApp", "Downloaded bytes: " + progress[0]); 
			textView.setText("aguarde"); 

		} 

		protected void onPostExecute(String result)
		{ 
			if (result != null)
			{
				
				textView.setText(result);
				
				//
			//	postoAtArray = new ArrayList<PostoAt>;
				
				/* try
				{
					JSONObject myjson = new JSONObject(result);
					JSONArray the_json_array = myjson.getJSONArray("PostosAtendimentoEletronico");
			
					int size = the_json_array.length();
					
					for(int i =0;i < size ; i++)
					{
						//PostoAt postoAt = new PostoAt();
						 the_json_array.get(i);
					}
					
					
				}
				catch (JSONException e)
				{}
				
				*/
				
				
				Log.d("MyApp", result);
			}
			else 
				Log.d("MyApp", "Download failed");

		}
	}
	
	
	
	/*
	private class DownloadTask extends AsyncTask<String, Long, File> 
	{ 
		protected File doInBackground(String... urls) 
		{ 
		try 
			{ 
				HttpRequest request = HttpRequest.get(urls[0]);
				File file = null;
				if (request.ok()) 
				{ 
					try
					{
						file = File.createTempFile("download", ".tmp");
					}
					catch (IOException e)
					{}
					
					request.body();
					
					
					request.receive(file); 
					
					publishProgress(file.length()); 
					
				}
				return file; 
				} catch (HttpRequestException exception) 
				{ 
				return null; 
				} 
				} 
				protected void onProgressUpdate(Long... progress) 
				{ 
					Log.d("MyApp", "Downloaded bytes: " + progress[0]); 
					textView.setText("Downloaded bytes: " + progress[0]); 
					
				} 
				
				protected void onPostExecute(File file)
				{ 
					if (file != null)
						Log.d("MyApp", "Downloaded file to: " + file.getAbsolutePath());
					else 
						Log.d("MyApp", "Download failed");
				
				}
	}		*/
	
	
	
}
