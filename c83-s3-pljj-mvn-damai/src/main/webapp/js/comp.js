 //自定义组件名，组件json
Vue.component('page-head',{
	   //反引号 Esc下面的按键  反引号定义的字符串 内部可以换行 不需要拼接
	   template:`
		   <div class="container header">
			
			
		   <div class="span5">
		   		<div class="logo">
		   			<a href="index.html">
		   				<img src="image/r___________renleipic_01/logo.png" alt="依依不舍"/>
		   			</a>
		   		</div>
		   	</div>
		   	<div class="span9">
		   <div class="headerAd">
		   	<img src="image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
		   </div>	</div>
		   <div class="span10 last">
		   		<div class="topNav clearfix">
		   			<ul>
		   					<li id="headerLogin" class="headerLogin" style="display: list-item;">
		   						Song|
		   					</li>
		   					<li id="headerLogin" class="headerLogin" style="display: list-item;">
		   						<a href="olist.html">我的订单</a>|
		   					</li>
		   					<li id="headerRegister" class="headerRegister" style="display: list-item;">
		   					<a href="index.html">退出</a>|
		   				</li>
		   				
		   				
		   				
		   				
		   				<li id="headerUsername" class="headerUsername"></li>
		   				<li id="headerLogout" class="headerLogout">
		   					<a>[退出]</a>|
		   				</li>
		   						<li>
		   							<a>会员中心</a>
		   							|
		   						</li>
		   						<li>
		   							<a>购物指南</a>
		   							|
		   						</li>
		   						<li>
		   							<a>关于我们</a>
		   							
		   						</li>
		   			</ul>
		   		</div>
		   		<div class="cart">
		   			<a  href="cart.html">购物车</a>
		   		</div>
		   			<div class="phone">
		   				客服热线:
		   				<strong>96008/53277764</strong>
		   			</div>
		   	</div>
		   	


		   <div id="nb"class="span24">
		   		<ul class="mainNav">
		   					<li>
		   						<a href="index.html">首页</a>
		   						|
		   					</li>
		   					
		   					<li>
		   <a href="clist.html?#1&pageIndex=1">
		   					女装男装
		   </a>
		   					|</li>
		   					
		   					<li>
		   <a href="clist.html?#2&pageIndex=1">
		   					鞋靴箱包
		   </a>
		   					|</li>
		   					
		   					<li>
		   <a href="clist.html?#3&pageIndex=1">
		   					运动户外
		   </a>
		   					|</li>
		   					
		   					<li>
		   <a href="clist.html?#4&pageIndex=1">
		   					珠宝配饰
		   </a>
		   					|</li>
		   					
		   					<li>
		   <a href="clist.html?#5&pageIndex=1">
		   					手机数码
		   </a>
		   					|</li>
		   					
		   					<li>
		   <a href="clist.html?#6&pageIndex=1">
		   					家电办公
		   </a>
		   					|</li>
		   					
		   					<li>
		   <a href="clist.html?#7&pageIndex=1">
		   					护肤彩妆
		   </a>
		   					|</li>
		   							
		   		</ul>
		   	</div>

		   </div>	
	   `
   });

Vue.component('page-foot',{
	template:`
	<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a>关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a>招贤纳士</a>
						|
					</li>
					<li>
						<a>法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a>服务声明</a>
						|
					</li>
					<li>
						<a>广告声明</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 大麦商城 版权所有</div>
	</div>
</div>
	`
});

Vue.component('friend-link',{
	template:`
	<div class="span24">
			<div class="friendLink">
				<dl>
					<dd>新手指南</dd>
							<dd>
								<a  target="_blank">支付方式</a>
								|
							</dd>
							<dd>
								<a  target="_blank">配送方式</a>
								|
							</dd>
							<dd>
								<a  target="_blank">售后服务</a>
								|
							</dd>
							<dd>
								<a  target="_blank">购物帮助</a>
								|
							</dd>
							<dd>
								<a  target="_blank">蔬菜卡</a>
								|
							</dd>
							<dd>
								<a  target="_blank">礼品卡</a>
								|
							</dd>
							<dd>
								<a target="_blank">银联卡</a>
								|
							</dd>
							<dd>
								<a  target="_blank">亿家卡</a>
								|
							</dd>
							
					<dd class="more">
						<a >更多</a>
					</dd>
				</dl>
			</div>
		</div>
	`
});