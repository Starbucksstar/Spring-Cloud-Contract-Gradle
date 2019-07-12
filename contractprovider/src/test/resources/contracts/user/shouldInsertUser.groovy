import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        headers {
            header('Content-Type': 'application/json;charset=UTF-8')
        }
        url '/user/insertUser'
        body(
            "id" : 123L ,
            "name" : "star" ,
            "age" : 24 ,
            "address" : "湖北武汉"
        )

    }
    response {
        status 200
        body("添加用户成功")
        headers {
            header('Content-Type': 'application/json;charset=UTF-8')
        }
    }

}