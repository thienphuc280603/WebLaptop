const app = angular.module("app", []);
app.controller("myctrl", function($scope, $http) {
	$scope.cart = {
		items: [],
		add(id) {
			var item = this.items.find(item => item.productid == id);
			if (item) {
				item.qty++;
				this.saveToLocalStorage();
			} else {
				$http.get(`rest/products/${id}`).then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				})
			}
		},

		remove(id) {
			var index = this.items.findIndex(item => item.productid == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		clear() {
			this.items = []
			this.saveToLocalStorage();
		},
		get count() {
			return this.items
				.map(item => item.qty)
				.reduce((total, qty) => total += qty, 0);
		},
		get amount() {
			return this.items
				.map(item => item.qty * item.price)
				.reduce((total, qty) => total += qty, 0);
		},
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},
		loadFromLocalStorage() {
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		},
	}
	$scope.cart.loadFromLocalStorage();

	$scope.order = {
		createDate: new Date(),
		address: "",
		active: true,
		user: { userid: parseInt($("#username").text()) },
		get items() {
			return $scope.cart.items.map(item => {
				return {
					product: { productid: item.productid },
					price: item.price,
					quantity: item.qty
				};
			});
		},
		purchase() {
			var order = angular.copy(this);
			
			if ($scope.cart.count === 0) {
            alert("Giỏ hàng của bạn đang trống. Vui lòng thêm sản phẩm vào giỏ hàng trước khi đặt hàng.");
            return;
        }

			$http.post("/api/orders", order).then(resp => {
				alert("Đặt hàng thành công");
				$scope.cart.clear();
				location.href = "/order/detail" + resp.data.id;
			}).catch(error => {
				alert("Lỗi");
				console.log(error);
			});
		}
	};

});

$(document).ready(function() {
	$("button[ng-click^='cart.add']").click(function() {
		$(".overlay").fadeIn();
		$(".added-message").fadeIn();
		setTimeout(function() {
			$(".overlay").fadeOut();
			$(".added-message").fadeOut();
		}, 2000); // Ẩn lớp phủ và dòng chữ sau 2 giây
	});
});
