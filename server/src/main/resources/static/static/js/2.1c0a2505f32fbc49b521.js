webpackJsonp([2],{"+FJC":function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i={props:{dataSource:Number},data:function(){return{wareModal:!1,wareColumns:[{title:"ware_id",key:"ware_id",sortable:!0},{title:"size",key:"size",sortable:!0},{title:"produce_place",key:"produce_place",sortable:!0},{title:"price",slot:"price",sortable:!0,filters:[{label:"0 ~ 50",value:1},{label:"50 ~ 80",value:2},{label:"80 ~ 100",value:3}],filterMultiple:!1,filterMethod:function(e,t){return 1===e?t.price<=50:2===e?t.price>50&&t.price<=80:3===e?t.price>80:void 0}},{title:"inventory",slot:"inventory",sortable:!0,filters:[{label:"0 ~ 200",value:1},{label:"200 ~ 500",value:2},{label:"500 ~ 1000",value:3}],filterMultiple:!1,filterMethod:function(e,t){return 1===e?t.inventory<=200:2===e?t.inventory>200&&t.inventory<=500:3===e?t.inventory>500:void 0}},{title:"操作",slot:"action",align:"center"}],wareData:[],createGoodModal:!1,createGoodForm:{good_name:"",delivery_fee:0,description:""},inventoryColumns:[{title:"good_name",key:"good_name",sortable:!0},{title:"delivery_fee",slot:"delivery_fee",sortable:!0,filters:[{label:"<=5",value:1},{label:">5",value:2}],filterMultiple:!1,filterMethod:function(e,t){return 1===e?t.delivery_fee<=5:2===e?t.delivery_fee>5:void 0}},{title:"description",slot:"description",sortable:!0},{title:"操作",slot:"action",align:"center"}],inventoryData:[],editGoodIndex:-1,editGood_name:"",editDelivery_fee:0,editDescription:"",editWareIndex:-1,editPrice:0,editInventory:0,allWareList:[],createOrderModal:!1,current_delivery_fee:0,current_good_name:"",current_ware_id:"",current_ware_price:0,createOrderForm:{order_id:"OrderID",buyer_account_id:"AccountId000000",good_name:"",size:"",produce_place:"",amount:0,year:0,month:0,day:0,price:0,receive_address:""}}},mounted:function(){var e,t=this;e=1==this.dataSource?"/good/info":"/g/good/info",this.$axios.get(e).then(function(e){t.inventoryData=e.data,t.$Message.success("成功加载Good数据")}).catch(function(e){t.$Message.error("加载Good数据失败")}),e=1==this.dataSource?"/ware/info":"/g/ware/info",this.$axios.get(e).then(function(e){t.allWareList=e.data,t.$Message.success("成功加载Ware数据")}).catch(function(e){t.$Message.error("加载Ware数据失败")})},methods:{submitOrder:function(){for(var e=0;e<this.allWareList.length;e++)if(this.allWareList[e].good_name===this.current_good_name&&this.allWareList[e].ware_id===this.current_ware_id){this.allWareList[e].inventory-=this.createOrderForm.amount;break}this.createOrderForm.price=this.createOrderForm.amount*this.current_ware_price,this.$emit("createOrder",this.createOrderForm),this.createOrderModal=!1},buy:function(e){this.current_ware_id=e.ware_id,this.current_ware_price=e.price,this.createOrderForm.order_id="",this.createOrderForm.good_name=this.current_good_name,this.createOrderForm.size=e.size,this.createOrderForm.produce_place=e.produce_place,this.createOrderForm.amount=0;var t=new Date;this.createOrderForm.year=t.getFullYear(),this.createOrderForm.month=t.getMonth()+1,this.createOrderForm.day=t.getDate(),this.createOrderForm.price=0,this.createOrderForm.receive_address="",this.createOrderModal=!0},submit:function(){var e={good_name:this.createGoodForm.good_name,delivery_fee:this.createGoodForm.delivery_fee,description:this.createGoodForm.description};this.inventoryData.push(e);var t={good_name:this.createGoodForm.good_name,ware_id:0,size:"big",produce_place:"native",price:0,inventory:0};this.allWareList.push(t);var r={good_name:this.createGoodForm.good_name,ware_id:1,size:"big",produce_place:"imported",price:0,inventory:0};this.allWareList.push(r);var i={good_name:this.createGoodForm.good_name,ware_id:2,size:"small",produce_place:"native",price:0,inventory:0};this.allWareList.push(i);var a={good_name:this.createGoodForm.good_name,ware_id:3,size:"small",produce_place:"imported",price:0,inventory:0};this.allWareList.push(a),this.createGoodModal=!1},createGood:function(){this.createGoodModal=!0},remove:function(e){var t=this.inventoryData[e].good_name;this.inventoryData.splice(e,1);for(var r=0;r<this.allWareList.length;)this.allWareList[r].good_name===t?this.allWareList.splice(r,1):r++},handleEdit:function(e,t,r){1==r?(this.editGood_name=e.good_name,this.editDelivery_fee=e.delivery_fee,this.editDescription=e.description,this.editGoodIndex=t):2==r&&(this.editPrice=e.price,this.editInventory=e.inventory,this.editWareIndex=t)},handleSave:function(e,t){1==t?(this.inventoryData[e].good_name=this.editGood_name,this.inventoryData[e].delivery_fee=this.editDelivery_fee,this.inventoryData[e].description=this.editDescription,this.editGoodIndex=-1):2==t&&(this.wareData[e].price=this.editPrice,this.wareData[e].inventory=this.editInventory,this.editWareIndex=-1)},wareInfo:function(e){this.wareData=[];var t=this.inventoryData[e].good_name;this.current_good_name=t,this.current_delivery_fee=this.inventoryData[e].delivery_fee;for(var r=0;r<this.allWareList.length;r++)this.allWareList[r].good_name==t&&this.wareData.push(this.allWareList[r]);this.wareModal=!0},upLoad:function(){var e,t=this;e=1==this.dataSource?"/good/update":"/g/good/update",this.$axios.post(e,{list:this.inventoryData}).then(function(e){t.$Message.success("成功将Good数据同步至Hive")}).catch(function(e){t.$Message.error("同步Good数据失败！")}),e=1==this.dataSource?"/ware/update":"/g/ware/update",this.$axios.post(e,{list:this.allWareList}),then(function(e){t.$Message.success("成功将Ware数据同步至Hive")}).catch(function(e){t.$Message.error("同步Ware数据失败！")})}}},a={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("Modal",{attrs:{width:"1000"},model:{value:e.wareModal,callback:function(t){e.wareModal=t},expression:"wareModal"}},[r("p",{staticStyle:{"text-align":"center","font-size":"20px",color:"cornflowerblue"},attrs:{slot:"header"},slot:"header"},[r("span",[e._v("Ware Info")])]),e._v(" "),r("Table",{staticStyle:{"margin-top":"20px"},attrs:{height:"500",border:"",columns:e.wareColumns,data:e.wareData},scopedSlots:e._u([{key:"price",fn:function(t){var i=t.row,a=t.index;return[e.editWareIndex===a?r("Input",{attrs:{type:"number"},model:{value:e.editPrice,callback:function(t){e.editPrice=t},expression:"editPrice"}}):r("span",[e._v(e._s(i.price))])]}},{key:"inventory",fn:function(t){var i=t.row,a=t.index;return[e.editWareIndex===a?r("Input",{attrs:{type:"number"},model:{value:e.editInventory,callback:function(t){e.editInventory=t},expression:"editInventory"}}):r("span",[e._v(e._s(i.inventory))])]}},{key:"action",fn:function(t){var i=t.row,a=t.index;return[e.editWareIndex===a?r("div",[r("Button",{staticStyle:{"margin-right":"5px"},attrs:{type:"success",size:"small"},on:{click:function(t){return e.handleSave(a,2)}}},[e._v("保存")]),e._v(" "),r("Button",{attrs:{type:"warning",size:"small"},on:{click:function(t){e.editWareIndex=-1}}},[e._v("取消")])],1):r("div",[r("Button",{staticStyle:{"margin-right":"5px"},attrs:{type:"primary",size:"small"},on:{click:function(t){return e.handleEdit(i,a,2)}}},[e._v("修改")]),e._v(" "),r("Button",{attrs:{type:"info",size:"small"},on:{click:function(t){return e.buy(i)}}},[e._v("购买")])],1)]}}])})],1),e._v(" "),r("Modal",{attrs:{width:"400"},model:{value:e.createOrderModal,callback:function(t){e.createOrderModal=t},expression:"createOrderModal"}},[r("p",{staticStyle:{"text-align":"center","font-size":"20px",color:"cornflowerblue"},attrs:{slot:"header"},slot:"header"},[r("span",[e._v("New Order")])]),e._v(" "),r("div",[r("Card",{staticStyle:{margin:"0 auto",width:"100%","margin-top":"10px"}},[r("div",{staticStyle:{display:"flex","justify-content":"flex-start"}},[r("div",[r("h4",[e._v("OrderID:")])]),e._v(" "),r("div",{staticStyle:{"margin-left":"10px"}},[r("Input",{attrs:{"show-word-limit":"",type:"textarea",placeholder:"Enter something...",maxlength:"15",autosize:{minRows:1}},model:{value:e.createOrderForm.order_id,callback:function(t){e.$set(e.createOrderForm,"order_id",t)},expression:"createOrderForm.order_id"}})],1)]),e._v(" "),r("div",{staticStyle:{display:"flex","justify-content":"flex-start"}},[r("div",[r("h4",[e._v("BuyerAccountID:")])]),e._v(" "),r("div",{staticStyle:{"margin-left":"10px"}},[r("p",[e._v(e._s(e.createOrderForm.buyer_account_id))])])]),e._v(" "),r("div",{staticStyle:{display:"flex","justify-content":"flex-start","margin-top":"8px"}},[r("div",[r("h4",[e._v("GoodName:")])]),e._v(" "),r("div",{staticStyle:{"margin-left":"10px"}},[r("p",[e._v(e._s(e.createOrderForm.good_name))])])]),e._v(" "),r("div",{staticStyle:{display:"flex","justify-content":"flex-start","margin-top":"8px"}},[r("div",[r("h4",[e._v("Size:")])]),e._v(" "),r("div",{staticStyle:{"margin-left":"10px"}},[r("p",[e._v(e._s(e.createOrderForm.size))])])]),e._v(" "),r("div",{staticStyle:{display:"flex","justify-content":"flex-start","margin-top":"8px"}},[r("div",[r("h4",[e._v("ProducePlace:")])]),e._v(" "),r("div",{staticStyle:{"margin-left":"10px"}},[r("p",[e._v(e._s(e.createOrderForm.produce_place))])])]),e._v(" "),r("div",{staticStyle:{display:"flex","justify-content":"flex-start","margin-top":"8px"}},[r("div",[r("h4",[e._v("Amount:")])]),e._v(" "),r("div",{staticStyle:{"margin-left":"10px"}},[r("Input",{attrs:{type:"number",placeholder:"Enter something...",autosize:{minRows:1}},model:{value:e.createOrderForm.amount,callback:function(t){e.$set(e.createOrderForm,"amount",t)},expression:"createOrderForm.amount"}})],1)]),e._v(" "),r("div",{staticStyle:{display:"flex","justify-content":"flex-start"}},[r("div",[r("h4",[e._v("Year:")])]),e._v(" "),r("div",{staticStyle:{"margin-left":"10px"}},[r("p",[e._v(e._s(e.createOrderForm.year))])])]),e._v(" "),r("div",{staticStyle:{display:"flex","justify-content":"flex-start","margin-top":"8px"}},[r("div",[r("h4",[e._v("Month:")])]),e._v(" "),r("div",{staticStyle:{"margin-left":"10px"}},[r("p",[e._v(e._s(e.createOrderForm.month))])])]),e._v(" "),r("div",{staticStyle:{display:"flex","justify-content":"flex-start","margin-top":"8px"}},[r("div",[r("h4",[e._v("Date:")])]),e._v(" "),r("div",{staticStyle:{"margin-left":"10px"}},[r("p",[e._v(e._s(e.createOrderForm.day))])])]),e._v(" "),r("div",{staticStyle:{display:"flex","justify-content":"flex-start","margin-top":"8px"}},[r("div",[r("h4",[e._v("Price:")])]),e._v(" "),r("div",{staticStyle:{"margin-left":"10px"}},[r("p",[e._v(e._s(e.createOrderForm.amount*e.current_ware_price+e.current_delivery_fee))])])]),e._v(" "),r("div",{staticStyle:{"margin-top":"8px"}},[r("h4",[e._v("ReceiveAddress:")]),e._v(" "),r("Input",{attrs:{"show-word-limit":"",type:"textarea",placeholder:"Enter something...",maxlength:"200",autosize:{minRows:2}},model:{value:e.createOrderForm.receive_address,callback:function(t){e.$set(e.createOrderForm,"receive_address",t)},expression:"createOrderForm.receive_address"}})],1)])],1),e._v(" "),r("div",{attrs:{slot:"footer"},slot:"footer"},[r("Button",{attrs:{type:"primary",size:"large",long:""},on:{click:function(t){return e.submitOrder()}}},[e._v("Submit")])],1)]),e._v(" "),r("Modal",{attrs:{width:"400"},model:{value:e.createGoodModal,callback:function(t){e.createGoodModal=t},expression:"createGoodModal"}},[r("p",{staticStyle:{"text-align":"center","font-size":"20px",color:"cornflowerblue"},attrs:{slot:"header"},slot:"header"},[r("span",[e._v("Create Good")])]),e._v(" "),r("Form",{staticStyle:{margin:"auto"},attrs:{model:e.createGoodForm}},[r("FormItem",{attrs:{label:"Name"}},[r("Input",{attrs:{"show-word-limit":"",type:"textarea",placeholder:"Enter something...",maxlength:"15",autosize:{minRows:1}},model:{value:e.createGoodForm.good_name,callback:function(t){e.$set(e.createGoodForm,"good_name",t)},expression:"createGoodForm.good_name"}})],1),e._v(" "),r("FormItem",{attrs:{label:"Dilivery Fee"}},[r("Input",{attrs:{type:"number",placeholder:"Enter something...",autosize:{minRows:1}},model:{value:e.createGoodForm.delivery_fee,callback:function(t){e.$set(e.createGoodForm,"delivery_fee",t)},expression:"createGoodForm.delivery_fee"}})],1),e._v(" "),r("FormItem",{attrs:{label:"Description"}},[r("Input",{attrs:{"show-word-limit":"",type:"textarea",placeholder:"Enter something...",maxlength:"200",autosize:{minRows:2}},model:{value:e.createGoodForm.description,callback:function(t){e.$set(e.createGoodForm,"description",t)},expression:"createGoodForm.description"}})],1)],1),e._v(" "),r("div",{attrs:{slot:"footer"},slot:"footer"},[r("Button",{attrs:{type:"primary",size:"large",long:""},on:{click:function(t){return e.submit()}}},[e._v("Submit")])],1)],1),e._v(" "),r("h1",{staticStyle:{"margin-top":"10px","text-align":"center"}},[e._v("Inventory")]),e._v(" "),r("Table",{staticStyle:{"margin-top":"20px"},attrs:{height:"500",border:"",columns:e.inventoryColumns,data:e.inventoryData},scopedSlots:e._u([{key:"good_name",fn:function(t){var i=t.row,a=t.index;return[e.editGoodIndex===a?r("Input",{attrs:{type:"text","show-word-limit":"",maxlength:"15"},model:{value:e.editGood_name,callback:function(t){e.editGood_name=t},expression:"editGood_name"}}):r("span",[e._v(e._s(i.good_name))])]}},{key:"description",fn:function(t){var i=t.row,a=t.index;return[e.editGoodIndex===a?r("Input",{attrs:{type:"text","show-word-limit":"",maxlength:"200"},model:{value:e.editDescription,callback:function(t){e.editDescription=t},expression:"editDescription"}}):r("span",[e._v(e._s(i.description))])]}},{key:"delivery_fee",fn:function(t){var i=t.row,a=t.index;return[e.editGoodIndex===a?r("Input",{attrs:{type:"number"},model:{value:e.editDelivery_fee,callback:function(t){e.editDelivery_fee=t},expression:"editDelivery_fee"}}):r("span",[e._v(e._s(i.delivery_fee))])]}},{key:"action",fn:function(t){var i=t.row,a=t.index;return[e.editGoodIndex===a?r("div",[r("Button",{staticStyle:{"margin-right":"5px"},attrs:{type:"success",size:"small"},on:{click:function(t){return e.handleSave(a,1)}}},[e._v("保存")]),e._v(" "),r("Button",{attrs:{type:"warning",size:"small"},on:{click:function(t){e.editGoodIndex=-1}}},[e._v("取消")])],1):r("div",[r("Button",{staticStyle:{"margin-right":"5px"},attrs:{type:"primary",size:"small"},on:{click:function(t){return e.handleEdit(i,a,1)}}},[e._v("修改")]),e._v(" "),r("Button",{attrs:{type:"info",size:"small"},on:{click:function(t){return e.wareInfo(a)}}},[e._v("具体信息")]),e._v(" "),r("Button",{attrs:{type:"error",size:"small"},on:{click:function(t){return e.remove(a)}}},[e._v("移除")])],1)]}}])}),e._v(" "),r("Button",{staticStyle:{"margin-top":"10px",width:"100px"},attrs:{type:"primary"},on:{click:e.createGood}},[r("Icon",{attrs:{type:"ios-add",size:"20"}}),e._v("\n    Add\n  ")],1),e._v(" "),r("Button",{staticStyle:{"margin-top":"10px","margin-left":"10px",width:"100px"},attrs:{type:"info"},on:{click:e.upLoad}},[r("Icon",{attrs:{type:"ios-cloud",size:"20"}}),e._v("\n    Upload\n  ")],1)],1)},staticRenderFns:[]};var o=r("VU/8")(i,a,!1,function(e){r("9t3o")},null,null);t.default=o.exports},"9t3o":function(e,t){}});
//# sourceMappingURL=2.1c0a2505f32fbc49b521.js.map