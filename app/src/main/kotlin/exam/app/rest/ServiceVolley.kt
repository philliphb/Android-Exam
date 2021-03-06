package exam.app.rest

import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import exam.app.App
import org.json.JSONObject

/**
 * Created by Mikkel on 25/05/2017.
 */
class ServiceVolley : ServiceInterface {
    val TAG = ServiceVolley::class.java.simpleName

    val basePath = "http://37.139.12.91:3000"

    override fun post(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        val jsonObjReq = object : JsonObjectRequest(Method.POST, basePath + path, params,
                Response.Listener<JSONObject> { response ->
                    Log.d(TAG, "/post request OK! Response: $response")
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    Log.d(TAG, "/post request fail! Error: ${error.message}")
                    VolleyLog.e(TAG, "/post request fail! Error: ${error.message}")
                    completionHandler(null)
                }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }

        App.instance?.addToRequestQueue(jsonObjReq, TAG)
    }
}