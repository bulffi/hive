package com.f4;

import com.f4.proto.skr.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.*;
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
        String connectionURL = "jdbc:hive2://localhost:10000";
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
                StringBuilder writeToFile = new StringBuilder();
                for (Account a :
                        request.getAccountListList()) {
                    String account_id = a.getAccountId();
                    String password = a.getPassword();
                    String nickname = a.getNickname();
                    int gender = a.getGender();
                    String description = a.getDescription();
                    writeToFile
                            .append(account_id).append("&")
                            .append(password).append("&")
                            .append(nickname).append("&")
                            .append(gender).append("&")
                            .append(description)
                            .append("\n");
                }
                File file = new File("/home/hive/data/account.txt");
                if(file.exists()){
                    file.delete();
                }
                System.out.println(writeToFile.toString());
                file.createNewFile();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(writeToFile.toString());
                bufferedWriter.flush();
                Statement statement = hiveConnection.createStatement();
                statement.execute("load data local inpath \"/home/hive/data/account.txt\" overwrite into table account");
                responseObserver.onNext(Nothing.newBuilder().build());
                responseObserver.onCompleted();
                statement.close();
            } catch (IOException | SQLException e){
                e.printStackTrace();
                responseObserver.onError(e);
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
                StringBuilder writeToFile = new StringBuilder();
                for (Coupon c :
                        request.getCouponListList()) {
                    writeToFile.append(c.getCouponId()).append("&")
                            .append(c.getDiscount()).append("&")
                            .append(c.getInventory()).append("\n");
                }
                File file = new File("/home/hive/data/coupon.txt");
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                System.out.println(writeToFile.toString());
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(writeToFile.toString());
                bufferedWriter.flush();
                Statement statement = hiveConnection.createStatement();
                statement.execute("load data local inpath \"/home/hive/data/coupon.txt\" overwrite into table coupon");
                responseObserver.onNext(Nothing.newBuilder().build());
                responseObserver.onCompleted();
                statement.close();
            } catch (SQLException | IOException e) {
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
                ResultSet resultSet = statement.executeQuery("select * from good");
                GoodInfo.Builder builder = GoodInfo.newBuilder();
                while (resultSet.next()){
                    String good_name = resultSet.getString("good_name");
                    String description = resultSet.getString("description");
                    double delivery_fee = resultSet.getDouble("delivery_fee");
                    Good good = Good.newBuilder()
                            .setGoodName(good_name)
                            .setDescription(description)
                            .setDeliveryFee(delivery_fee)
                            .build();
                    builder.addGoodList(good);
                }
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
                statement.close();
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
                StringBuilder writeToFile = new StringBuilder();
                for (Good g :
                        request.getGoodListList()) {
                    writeToFile.append(g.getGoodName()).append("&")
                            .append(g.getDescription()).append("&")
                            .append(g.getDeliveryFee()).append("\n");
                }
                File file = new File("/home/hive/data/good.txt");
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                System.out.println(writeToFile.toString());
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(writeToFile.toString());
                bufferedWriter.flush();
                Statement statement = hiveConnection.createStatement();
                statement.execute("load data local inpath \"/home/hive/data/good.txt\" overwrite into table good");
                responseObserver.onNext(Nothing.newBuilder().build());
                responseObserver.onCompleted();
                statement.close();
            }catch (SQLException | IOException e){
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
                OrderInfo.Builder builder = OrderInfo.newBuilder();
                ResultSet resultSet = statement.executeQuery("select * from order_form");
                while (resultSet.next()){
                    String order_id = resultSet.getString("order_id");
                    String buyer_account_id = resultSet.getString("buyer_account_id");
                    String good_name = resultSet.getString("good_name");
                    String size = resultSet.getString("size");
                    String place = resultSet.getString("produce_place");
                    int amount = resultSet.getInt("amount");
                    int year = resultSet.getInt("year");
                    int month = resultSet.getInt("month");
                    int day = resultSet.getInt("day");
                    double price = resultSet.getDouble("price");
                    String receive_address = resultSet.getString("receive_address");
                    Order order = Order.newBuilder()
                            .setOrderId(order_id)
                            .setBuyerAccountId(buyer_account_id)
                            .setGoodName(good_name)
                            .setSize(size)
                            .setProducePlace(place)
                            .setAmount(amount)
                            .setYear(year)
                            .setMonth(month)
                            .setDay(day)
                            .setPrice(price)
                            .setReceiveAddress(receive_address)
                            .build();
                    builder.addOrderList(order);
                }
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
                statement.close();
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
                StringBuilder writeToFile = new StringBuilder();
                for (Order o :
                        request.getOrderListList()) {
                    writeToFile.append(o.getOrderId()).append("&")
                            .append(o.getBuyerAccountId()).append("&")
                            .append(o.getGoodName()).append("&")
                            .append(o.getSize()).append("&")
                            .append(o.getProducePlace()).append("&")
                            .append(o.getAmount()).append("&")
                            .append(o.getYear()).append("&")
                            .append(o.getMonth()).append("&")
                            .append(o.getDay()).append("&")
                            .append(o.getPrice()).append("&")
                            .append(o.getReceiveAddress()).append("\n");
                }
                System.out.println(writeToFile.toString());
                File file = new File("/home/hive/data/order.txt");
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                System.out.println(writeToFile.toString());
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(writeToFile.toString());
                bufferedWriter.flush();
                Statement statement = hiveConnection.createStatement();
                statement.execute("load data local inpath \"/home/hive/data/order.txt\" overwrite into table order_form");
                responseObserver.onNext(Nothing.newBuilder().build());
                responseObserver.onCompleted();
                statement.close();
            }catch (SQLException | IOException e){
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
                ResultSet resultSet = statement.executeQuery("select * from receive_info");
                ReceiveInfo.Builder builder = ReceiveInfo.newBuilder();
                while (resultSet.next()){
                    builder.addReceiveList(Receive.newBuilder()
                            .setReceiveInfoId(resultSet.getString("receive_info_id"))
                            .setReceiverAccountId(resultSet.getString("receiver_account_id"))
                            .setPhoneNumber(resultSet.getString("phone_number"))
                            .setAddress(resultSet.getString("address"))
                            .build());
                }
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
                statement.close();
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
                StringBuilder writeToFile = new StringBuilder();
                for (Receive r :
                        request.getReceiveListList()) {
                    writeToFile.append(r.getReceiveInfoId()).append("&")
                            .append(r.getReceiverAccountId()).append("&")
                            .append(r.getPhoneNumber()).append("&")
                            .append(r.getAddress()).append("\n");
                }
                System.out.println(writeToFile.toString());
                File file = new File("/home/hive/data/receive.txt");
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                System.out.println(writeToFile.toString());
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(writeToFile.toString());
                bufferedWriter.flush();
                Statement statement = hiveConnection.createStatement();
                statement.execute("load data local inpath \"/home/hive/data/receive.txt\" overwrite into table receive_info");
                responseObserver.onNext(Nothing.newBuilder().build());
                responseObserver.onCompleted();
                statement.close();
            }catch (SQLException | IOException e){
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
                WareInfo.Builder builder = WareInfo.newBuilder();
                ResultSet set = statement.executeQuery("select * from ware");
                while (set.next()){
                    builder.addWareList(Ware.newBuilder()
                            .setGoodName(set.getString("good_name"))
                            .setWareId(set.getString("ware_id"))
                            .setSize(set.getString("size"))
                            .setProducePlace(set.getString("produce_place"))
                            .setPrice(set.getDouble("price"))
                            .setInventory(set.getInt("inventory"))
                            .build());
                }
                responseObserver.onNext(builder.build());
                responseObserver.onCompleted();
                statement.close();
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
                StringBuilder writeToFile = new StringBuilder();
                for (Ware w :
                        request.getWareListList()) {
                    writeToFile.append(w.getGoodName()).append("&")
                            .append(w.getWareId()).append("&")
                            .append(w.getSize()).append("&")
                            .append(w.getProducePlace()).append("&")
                            .append(w.getPrice()).append("&")
                            .append(w.getInventory()).append("\n");
                }
                System.out.println(writeToFile.toString());
                File file = new File("/home/hive/data/ware.txt");
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                System.out.println(writeToFile.toString());
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(writeToFile.toString());
                bufferedWriter.flush();
                Statement statement = hiveConnection.createStatement();
                statement.execute("load data local inpath \"/home/hive/data/ware.txt\" overwrite into table ware");
                responseObserver.onNext(Nothing.newBuilder().build());
                responseObserver.onCompleted();
                statement.close();
            }catch (SQLException | IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        RPCServerStarter serverStarter = new RPCServerStarter();
        serverStarter.startServer();
    }
}
