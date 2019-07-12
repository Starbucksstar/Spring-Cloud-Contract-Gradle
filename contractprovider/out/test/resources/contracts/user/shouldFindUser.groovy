import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'Post'
        url('/info') {
            queryParameters {
                parameter("id", "user")
            }
        }

    }
    response {
        status 200
        body("hello user")
        headers {
            header('Content-Type': 'application/json;charset=UTF-8')
        }
    }

}