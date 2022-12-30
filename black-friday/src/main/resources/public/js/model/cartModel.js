var cartModel = {

    // buy
    add : function (data, success) {
        czHttp.getJSON('./data/success.json', data, function (responseData) {
            if(responseData.isok){
                success(responseData);
            }
        });
    },

    // delete
    remove : function (data, success) {
        czHttp.getJSON('./data/success.json', data, function (responseData) {
            if(responseData.isok){
                success(responseData);
            }
        });
    },

    // modify
    changeNumber : function (data, success) {
        czHttp.getJSON('./data/success.json', data, function (responseData) {
            if(responseData.isok){
                success(responseData);
            }
        });
    },

    // analyse
    subtotal : function (success) {
        czHttp.getJSON('./data/orders.json', data, function (responseData) {
            if(responseData.isok){
                success(responseData);
            }
        });
    },

    // list
    list : function (success) {

        czHttp.getJSON('./data/orders.json', {}, function(responseData){
            success(responseData);
        });
    }
};