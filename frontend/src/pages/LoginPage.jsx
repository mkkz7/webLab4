import { useState } from 'react'
import './styles/LoginPage.css'
import { Header } from '../components/Header'
import { LoginForm } from '../components/auth/LoginForm'

function LoginPage(){
  return (
    <>
      <Header/>
      <LoginForm/>
    </>
  );
}

export default LoginPage;