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
		
		String url = "https://olinda.bcb.gov.br/olinda/servico/Informes_PostosDeAtendimentoEletronico/versao/v1/swagger-ui3#/";
		String url2 = "https://olinda.bcb.gov.br/olinda/servico/Informes_PostosDeAtendimentoEletronico/versao/v1/odata/PostosAtendimentoEletronico";
		
		textView.setText(url2);
		
		new DownloadTask().execute(url2);
		
		textView.append("depois do task");
		
    }
	
	
	
	private class DownloadTask extends AsyncTask<String, String, String> 
	{ 
		protected String doInBackground(String... urls) 
		{ 
			
			String result = null;
			try 
			{ 
				textView.append("antes do request");
				publishProgress("antes do request"); 
				HttpRequest request = HttpRequest.get(urls[0]);
				
				
				
					InputStream response = request.stream(); 

				try (Scanner scanner = new Scanner(response)) 
				{ 

					String responseBody = scanner.useDelimiter("\\}").next(); 
					System.out.println(responseBody);
					result = responseBody;
				}
				/*catch (IOException ioe)
				{

					System.out.println(ioe);
				}*/
				
				
				
				
				/*
				
				if (request.ok()) 
				{ 
					result = request.body();
					textView.append(result);
					Log.w("MyApp",result);	
					publishProgress("request ok"); 
				}
				else
				{
						
					textView.append("request nao ok");
					publishProgress("request nao ok"); 
					Log.w("MyApp",request.message());		
					return null;
				}
					*/
					
				return result; 
				
			} catch (HttpRequestException exception) 
			{ 
				
				publishProgress("exception"); 
				Log.w("MyApp", exception.getCause().getMessage());
				return null; 
			} 
		} 
		protected void onProgressUpdate(String msg) 
		{ 
			Log.w("MyApp", msg); 
			textView.append(msg); 

		} 

		protected void onPostExecute(String result)
		{ 
			if (result != null)
			{
				
				textView.append(result);
				
				Log.w("MyApp", result);
			}
			else 
				Log.w("MyApp", "Download failed");

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
	
	/*
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

	} */
	
}
