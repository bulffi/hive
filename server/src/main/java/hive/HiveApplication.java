package hive;

import com.f4.proto.skr.hiveGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiveApplication.class, args);
    }

    @Bean
    hiveGrpc.hiveBlockingStub getStub(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",8081).usePlaintext().build();
        return hiveGrpc.newBlockingStub(channel);
    }


}
