import { Header } from '../components/UI/Header.jsx'
import { useNavigate } from 'react-router-dom';
import "../styles/MainPage.css"

function MainPage() {
  const navigate = useNavigate();
  const handleLogIn = () => {
      navigate('/login');
  };

  const handleSignUp = () => {
      navigate('/register');
  };

  return (
      <div className="particles-container">
          <div className="main-container">
              <Header/>

              <div className="button-container">
                  <button className="auth-button login-btn" onClick={handleLogIn}>
                      <span className="btn-text">Log in</span>
                  </button>

                  <button className="auth-button signup-btn" onClick={handleSignUp}>
                      <span className="btn-text">Sign up</span>
                  </button>
              </div>

              <div className="info-section">
                  <p className="info-text">
                      Если долго мучаться - что нибудь получится
                  </p>
              </div>
          </div>
      </div>

  );
}

export default MainPage;