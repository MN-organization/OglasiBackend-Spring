package paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Credentials {

    @Value("${paypal.client.id}")
    public String id;
    @Value("${paypal.client.secret}")
    public String secret;
    /**
     *Set up the PayPal Java SDK environment with PayPal access credentials.
     *This sample uses SandboxEnvironment. In production, use LiveEnvironment.
     */
    private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
            "AedJKGm_wmMXLOaQpsHHUBPQoYnKBx9h2V9G0x1czzed8qz9vKBru_ZMwao5IaBYjelU6G9CDWYES8xl",
            "EDWZgFQNkRTUkmGF2OLLhfY4_4L0cw90pkOVStlZ_xbIOqorDfImWxlUMPuSCt0e39xZjOfUmcNPovkV");

    // ovo iznad ucitati iz propertisa
    /**
     *PayPal HTTP client instance with environment that has access
     *credentials context. Use to invoke PayPal APIs.
     */
     PayPalHttpClient client = new PayPalHttpClient(environment);

    /**
     *Method to get client object
     *
     *@return PayPalHttpClient client
     */
    public PayPalHttpClient client() {
        return this.client;
    }
}
