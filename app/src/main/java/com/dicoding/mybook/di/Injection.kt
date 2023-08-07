package com.dicoding.mybook.di

import com.dicoding.mybook.data.BookRepository

object Injection {
    fun provideRepository(): BookRepository {
        return BookRepository.getInstance()
    }
}