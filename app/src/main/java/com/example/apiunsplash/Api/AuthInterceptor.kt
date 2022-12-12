package com.example.apiunsplash.Api



import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //Chain -> cadeia 1 2 REQUISICAO 4 5
        val requisicaoBuilder = chain.request().newBuilder()

        //definindo o cabeçalho
        val requisicao = requisicaoBuilder.addHeader(
            "Authorization", "Client-ID ${RetrofitService.CLIENT_ID}"
        ).build()

        return chain.proceed( requisicao )

    }

}
