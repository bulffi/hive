webpackJsonp([3],{Cj8b:function(e,t){},KTvf:function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n={props:{newOrderList:Array,dataSource:Number},data:function(){return{createOrderModal:!1,createOrderForm:{},orderColumns:[{title:"order_id",key:"order_id",sortable:!0},{title:"buyer_account_id",key:"buyer_account_id",sortable:!0},{title:"good_name",key:"good_name",sortable:!0},{title:"size",key:"size",sortable:!0},{title:"produce_place",key:"produce_place",sortable:!0},{title:"amount",key:"amount",sortable:!0},{title:"year",key:"year",sortable:!0},{title:"month",key:"month",sortable:!0},{title:"day",key:"day",sortable:!0},{title:"price",key:"price",sortable:!0},{title:"receive_address",key:"receive_address",sortable:!0},{title:"操作",slot:"action",align:"center"}],orderData:[]}},mounted:function(){var e,t=this;e=1==this.dataSource?"/order/info":"/g/order/info",this.$axios.get(e).then(function(e){t.orderData=e.data;for(var r=0;r<t.newOrderList.length;r++)t.orderData.push(t.newOrderList[r]);t.$emit("clearNewOrderList"),t.$Message.success("成功加载Order数据")}).catch(function(e){t.$Message.error("加载Order数据失败")})},methods:{upLoad:function(){var e,t=this;e=1==this.dataSource?"/order/update":"/g/order/update",this.$axios.post(e,{list:this.orderData}).then(function(e){t.$Message.success("成功将Order数据同步至数据库")}).catch(function(e){t.Message.error("同步Order数据失败！")})},remove:function(e){this.orderData.splice(e,1)}}},a={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("h1",{staticStyle:{"margin-top":"10px","text-align":"center"}},[e._v("Order")]),e._v(" "),r("Table",{staticStyle:{"margin-top":"20px"},attrs:{height:"500",border:"",columns:e.orderColumns,data:e.orderData},scopedSlots:e._u([{key:"order_id",fn:function(t){var n=t.row,a=t.index;return[e.editIndex===a?r("Input",{attrs:{type:"text"},model:{value:e.editOrder_id,callback:function(t){e.editOrder_id=t},expression:"editOrder_id"}}):r("span",[e._v(e._s(n.order_id))])]}},{key:"buyer_account_id",fn:function(t){var n=t.row,a=t.index;return[e.editIndex===a?r("Input",{attrs:{type:"text"},model:{value:e.editBuyer_account_id,callback:function(t){e.editBuyer_account_id=t},expression:"editBuyer_account_id"}}):r("span",[e._v(e._s(n.buyer_account_id))])]}},{key:"good_name",fn:function(t){var n=t.row,a=t.index;return[e.editIndex===a?r("Input",{attrs:{type:"text"},model:{value:e.editGood_name,callback:function(t){e.editGood_name=t},expression:"editGood_name"}}):r("span",[e._v(e._s(n.good_name))])]}},{key:"size",fn:function(t){var n=t.row,a=t.index;return[e.editIndex===a?r("Input",{attrs:{type:"text"},model:{value:e.editSize,callback:function(t){e.editSize=t},expression:"editSize"}}):r("span",[e._v(e._s(n.size))])]}},{key:"produce_place",fn:function(t){var n=t.row,a=t.index;return[e.editIndex===a?r("Input",{attrs:{type:"text"},model:{value:e.editProduce_place,callback:function(t){e.editProduce_place=t},expression:"editProduce_place"}}):r("span",[e._v(e._s(n.produce_place))])]}},{key:"amount",fn:function(t){var n=t.row,a=t.index;return[e.editIndex===a?r("Input",{attrs:{type:"number"},model:{value:e.editAmount,callback:function(t){e.editAmount=t},expression:"editAmount"}}):r("span",[e._v(e._s(n.amount))])]}},{key:"year",fn:function(t){var n=t.row,a=t.index;return[e.editIndex===a?r("Input",{attrs:{type:"number"},model:{value:e.editYear,callback:function(t){e.editYear=t},expression:"editYear"}}):r("span",[e._v(e._s(n.year))])]}},{key:"month",fn:function(t){var n=t.row,a=t.index;return[e.editIndex===a?r("Input",{attrs:{type:"number"},model:{value:e.editMonth,callback:function(t){e.editMonth=t},expression:"editMonth"}}):r("span",[e._v(e._s(n.month))])]}},{key:"day",fn:function(t){var n=t.row,a=t.index;return[e.editIndex===a?r("Input",{attrs:{type:"number"},model:{value:e.editDay,callback:function(t){e.editDay=t},expression:"editDay"}}):r("span",[e._v(e._s(n.day))])]}},{key:"price",fn:function(t){var n=t.row,a=t.index;return[e.editIndex===a?r("Input",{attrs:{type:"number"},model:{value:e.editPrice,callback:function(t){e.editPrice=t},expression:"editPrice"}}):r("span",[e._v(e._s(n.price))])]}},{key:"action",fn:function(t){t.row;var n=t.index;return[r("Button",{attrs:{type:"error",size:"small"},on:{click:function(t){return e.remove(n)}}},[e._v("移除")])]}}])}),e._v(" "),r("Button",{staticStyle:{"margin-top":"10px","margin-left":"10px",width:"100px"},attrs:{type:"info"},on:{click:e.upLoad}},[r("Icon",{attrs:{type:"ios-cloud",size:"20"}}),e._v("\n    Upload\n  ")],1)],1)},staticRenderFns:[]};var i=r("VU/8")(n,a,!1,function(e){r("Cj8b")},null,null);t.default=i.exports}});
//# sourceMappingURL=3.a9a9db587439601b304d.js.map