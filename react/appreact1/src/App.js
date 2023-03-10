import { Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from './components/Navbar';
import Home from './components/Home';
import About from './components/About';
import Contact from './components/Contact';
import Login from './components/Login';
import Profile from './components/Profile';
import SignUp from './components/SignUp';
import Property from './components/Property';
import PropertyForm from './components/PropertyForm';
import Client from './components/Client';
import ClientForm from './components/ClientForm';
import Payment from './components/Payment';
import PaymentForm from './components/PaymentForm';
import homeWall from "./Images/Screenshot 2023-03-09 002356.png"
import Footer from './components/Footer';


function App() {
  return (
    <div>
      <Navbar />
      <div className='dynamicOuterDiv'>
        <div className='dynamicImageDiv'>
          <img src={homeWall} width="100%" alt="background"></img>
        </div>
        <div className="dynamicInnerDiv">
          <Routes>
            <Route path='/' element={<Home />} />
            <Route path='/about' element={<About />} />
            <Route path='/contact' element={<Contact />} />
            <Route path='/login' element={<Login />} />
            <Route path='/profile' element={<Profile />} />
            <Route path='/signup' element={<SignUp />} />
            <Route path='/property' element={<Property />} />
            <Route path='/propertyform' element={<PropertyForm />} />
            <Route path='/property/view/:id' element={<Client />} />
            <Route path='/clientform/:id' element={<ClientForm />} />
            <Route path='/client/view/:id' element={<Payment />} />
            <Route path='/payemntform/:id' element={<PaymentForm />} />
          </Routes>
        </div>
      </div>

      <div className='footerContainer'>
        <Footer/>
      </div>


    </div>
  );
}

export default App;