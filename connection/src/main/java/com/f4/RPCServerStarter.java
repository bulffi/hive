package com.f4;

import com.f4.proto.skr.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.sql.*;

/**
 * @program: hive
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/08
 **/
public class RPCServerStarter {
    private  Server server;
    private static   Connection hiveConnection;

    public void startServer() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(8081);
        String connectionURL = "jdbc:hive2://118.31.76.206:10000";
        String username = "hive";
        String passwd = "Zzj6p@saturn@lym";
        String driverName = "org.apache.hive.jdbc.HiveDriver";
        Class.forName(driverName);
        hiveConnection = DriverManager.getConnection(connectionURL, username, passwd);
        server = serverBuilder.addService(new Producer()).build();
        System.out.println("Start a server on port " + 8081);
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                try {
                    hiveConnection.close();
                    System.out.println("Close the hive connection and exit...");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                RPCServerStarter.this.stop();
            }
        });
        blockUntilShutdown();
    }

    private void stop(){
        if(server!=null){
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException{
        if(server!=null){
            server.awaitTermination();
        }
    }
    private static class Producer extends hiveGrpc.hiveImplBase{
        @Override
        public void getAccount(com.f4.proto.skr.Nothing request,
                               io.grpc.stub.StreamObserver<com.f4.proto.skr.AccountInfo> responseObserver) {
            try {
                Statement statement = hiveConnection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from account");
                AccountInfo.Builder builder = AccountInfo.newBuilder();
                while (resultSet.next()){
                    String account_id = resultSet.getString("account_id");
                    String password = resultSet.getString("password");
                    String nickName = resultSet.getString("nickname");
                    int gender = resultSet.getInt("gender");
                    String description = resultSet.getString("description");
                    Account account = Account.newBuilder()
                            .setAccountId(account_id)
                            .setPassword(password)
                            .setNickname(nickName)
                            .setGender(gender)
                            .setDescription(description)
                            .build();
                    builder.addAccountList(account);
                }
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
                responseObserver.onError(e);
            }
        }

        /**
         */
        @Override
        public void updateAccountInfo(com.f4.proto.skr.AccountInfo request,
                                      io.grpc.stub.StreamObserver<com.f4.proto.skr.Nothing> responseObserver) {
            try {
                Statement statement = hiveConnection.createStatement();



            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        /**
         */
        @Override
        public void getCoupon(com.f4.proto.skr.Nothing request,
                              io.grpc.stub.StreamObserver<com.f4.proto.skr.CouponInfo> responseObserver) {
            try {
                Statement statement = hiveConnection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from coupon");
                CouponInfo.Builder builder = CouponInfo.newBuilder();
                while (resultSet.next()){
                    String coupon_id = resultSet.getString("coupon_id");
                    double discount = resultSet.getDouble("discount");
                    int inventory = resultSet.getInt("inventory");
                    Coupon coupon = Coupon.newBuilder()
                            .setCouponId(coupon_id)
                            .setDiscount(discount)
                            .setInventory(inventory)
                            .build();
                    builder.addCouponList(coupon);
                }
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
                responseObserver.onError(e);
            }
        }

        /**
         */
        @Override
        public void setCouponInfo(com.f4.proto.skr.CouponInfo request,
                                  io.grpc.stub.StreamObserver<com.f4.proto.skr.Nothing> responseObserver) {
            try {
                Statement statement = hiveConnection.createStatement();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        /**
         */
        @Override
        public void getGood(com.f4.proto.skr.Nothing request,
                            io.grpc.stub.StreamObserver<com.f4.proto.skr.GoodInfo> responseObserver) {
            try {
                Statement statement = hiveConnection.createStatement();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        /**
         */
        @Override
        public void setGoodInfo(com.f4.proto.skr.GoodInfo request,
                                io.grpc.stub.StreamObserver<com.f4.proto.skr.Nothing> responseObserver) {
            try {
                Statement statement = hiveConnection.createStatement();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        /**
         */
        @Override
        public void getOrder(com.f4.proto.skr.Nothing request,
                             io.grpc.stub.StreamObserver<com.f4.proto.skr.OrderInfo> responseObserver) {
            try {
                Statement statement = hiveConnection.createStatement();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        /**
         */
        @Override
        public void setOrderInfo(com.f4.proto.skr.OrderInfo request,
                                 io.grpc.stub.StreamObserver<com.f4.proto.skr.Nothing> responseObserver) {
            try {
                Statement statement = hiveConnection.createStatement();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        /**
         */
        @Override
        public void getReceive(com.f4.proto.skr.Nothing request,
                               io.grpc.stub.StreamObserver<com.f4.proto.skr.ReceiveInfo> responseObserver) {
            try {
                Statement statement = hiveConnection.createStatement();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        /**
         */
        @Override
        public void setReceiveInfo(com.f4.proto.skr.ReceiveInfo request,
                                   io.grpc.stub.StreamObserver<com.f4.proto.skr.Nothing> responseObserver) {
            try {
                Statement statement = hiveConnection.createStatement();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        /**
         */
        @Override
        public void getWare(com.f4.proto.skr.Nothing request,
                            io.grpc.stub.StreamObserver<com.f4.proto.skr.WareInfo> responseObserver) {
            try {
                Statement statement = hiveConnection.createStatement();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        /**
         */
        @Override
        public void setWareInfo(com.f4.proto.skr.WareInfo request,
                                io.grpc.stub.StreamObserver<com.f4.proto.skr.Nothing> responseObserver) {
            try {
                Statement statement = hiveConnection.createStatement();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        RPCServerStarter serverStarter = new RPCServerStarter();
        serverStarter.startServer();
    }
}
