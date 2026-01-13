import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "../pages/LoginPage.jsx";
import RegisterPage from "../pages/RegisterPage.jsx";
import MainPage from "../pages/MainPage.jsx";
import {UserPage} from "../pages/UserPage.jsx";
import {PrivateRoute} from "./PrivateRoute.jsx";

function App() {
  return (
    <BrowserRouter basename="/backend-app">
      <Routes>
        <Route path="login" element={<LoginPage />} />
        <Route path="register" element={<RegisterPage />} />
          <Route path="/" element={<MainPage/>} />

          <Route path="user" element={<PrivateRoute />}>
              <Route index element={<UserPage />} />
          </Route>
      </Routes>
    </BrowserRouter>

  );
}

export default App
