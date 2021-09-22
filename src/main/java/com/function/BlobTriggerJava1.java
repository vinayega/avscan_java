package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
//import fi.solita.clamav.*;


/**
 * Azure Functions with Azure Blob trigger.
 */
public class BlobTriggerJava1 {
    /**
     * This function will be invoked when a new or updated blob is detected at the specified path. The blob contents are provided as input to this function.
     * @throws Exception
     */
    @FunctionName("BlobTriggerJava1")
    @StorageAccount("teststorageaccount")
    public void run(
        @BlobTrigger(name = "content", path = "dropbox/{name}", dataType = "binary") byte[] content,
        @BindingName("name") String name,
        final ExecutionContext context
    ) throws Exception {
        
        ClamAVClient cl = new ClamAVClient("52.251.77.183", 3310);
        byte[] reply;
        try {
          reply = cl.scan(content);
        } catch (Exception e) {
          throw new RuntimeException("Could not scan the input", e);
        }
        if (!ClamAVClient.isCleanReply(reply)) {
          context.getLogger().info("aaargh. Something was found");
        }

        context.getLogger().info("Java Blob trigger function processed a blob. Name: " + name + "\n  Size: " + content.length + " Bytes");
    }
}



