import java.util.logging.Logger;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.catalyst.advanced.CatalystAdvancedIOHandler;
import com.zc.component.circuits.ZCCircuit;
import com.zc.component.circuits.ZCCircuitDetails;
import com.zc.component.circuits.ZCCircuitExecutionDetails;
import com.zc.component.object.ZCObject;
import com.zc.component.object.ZCRowObject;
import com.zc.component.object.ZCTable;
import com.zc.component.zcql.ZCQL;

public class TestFunction implements CatalystAdvancedIOHandler {
	private static final Logger LOGGER = Logger.getLogger(TestFunction.class.getName());
	
	@Override
    public void runner(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			switch(request.getRequestURI()) {
				case "/": {
					Date currentDate = new Date();
					ZCCircuitDetails userBackupCircuit = ZCCircuit.getInstance().getCircuitInstance(1926000009233184L);
					JSONObject execInputJson = new JSONObject();
					ZCCircuitExecutionDetails circuitExecution = userBackupCircuit.execute("Case1" + currentDate.getTime(), execInputJson);
					String executionId = circuitExecution.getExecutionId();
					userBackupCircuit.abortExecution(executionId);
					Thread.sleep(5000);
					ZCCircuitExecutionDetails execDetails = userBackupCircuit.getExecutionDetails(executionId);
					// ZCObject object = ZCObject.getInstance(); 
					// ZCTable tab = object.getTable("table1");
					// ZCRowObject row = ZCRowObject.getInstance();
					// row.set("integer1", 25); 
					// ZCRowObject test = tab.insertRow(row);
					// ZCObject object = ZCObject.getInstance(); 
					// ZCTable table = object.getTable("table1"); 
					// String query = "insert into table1 values ('qwerty',90)";
					// ZCRowObject rowDetails = table.getRow(1926000009194018L);
					response.setStatus(200);
					response.getWriter().write(execDetails.getStatus().toString());
					break;	
				}
				case "/test":{
					response.setStatus(200);
					response.getWriter().write("Test Case");
					break;
				}
				default: {
					response.setStatus(404);
					response.getWriter().write("You might find the page you are looking for at \"/\" path");
				}
			}
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE,"Exception in TestFunction",e);
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
			response.setStatus(500);
			response.getWriter().write(e.toString() + "\n" + sw.toString());
		}
	}
	
}