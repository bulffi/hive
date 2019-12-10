package hive;

import com.f4.proto.nn.MasterGrpc;
import com.f4.proto.skr.hiveGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HiveApplication {
    @Value("${hiveConnection.ip}")
    private String hiveConnectionIP;
    @Value("${hiveConnection.port}")
    private int hiveConnectionPort;
    @Value("${gDFSConnection.ip}")
    private String gDFSConnectionIP;
    @Value("${gDFSConnection.port}")
    private int gDFSConnectionPort;

    public static void main(String[] args) {
        SpringApplication.run(HiveApplication.class, args);
    }

    @Bean
    hiveGrpc.hiveBlockingStub getStub(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress(hiveConnectionIP,hiveConnectionPort).usePlaintext().build();
        return hiveGrpc.newBlockingStub(channel);
    }

    @Bean
    MasterGrpc.MasterBlockingStub getGDFSStub(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress(gDFSConnectionIP,gDFSConnectionPort).usePlaintext().build();
        return  MasterGrpc.newBlockingStub(channel);
    }

}
