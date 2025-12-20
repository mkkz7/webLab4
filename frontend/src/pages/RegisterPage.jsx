import './styles/RegisterPage.css'
import { useState } from 'react'
import { Header } from '../components/Header'
import { RegisterForm } from '../components/auth/RegisterForm'

function RegisterPage(){
  return (
    <>
      <Header/>
      <RegisterForm/>
    </>
  );
}

export default RegisterPage;