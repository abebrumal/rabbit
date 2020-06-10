package com.example.whiterabbit.data.network

import java.io.IOException


class CustomApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)
