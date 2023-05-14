import * as React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/Map/Home";
import City from "./components/Map/city/City";
import Gu from "./components/Map/gu/Gu";
import LogIn from "./components/User/Login";
import SignUp from "./components/User/SignUp";
import EditUser from "./components/User/EditUser";
import MyPage from "./components/User/MyPage";
import EditUserComplete from "./components/User/EditUserComplete";
import SignUpComplete from "./components/User/SignUpComplete";
import ListBoard from "./components/Board/ListBoard";
import UploadBoard from "./components/Board/UploadBoard";
import DetailBoard from "./components/Board/DetailBoard";
import Navbar from "./components/Navbar";
import FindId from "./components/User/FindId";

function App() {
    return(
        <BrowserRouter>
            <div className="App">
                <Navbar />
                <Routes>

                    // User
                    <Route path="/user/login" element={<LogIn />} />
                    <Route path="/user/signUp" element={<SignUp />} />
                    <Route path="/user/editUser" element={<EditUser />} />
                    <Route path="/user/editUserComplete" element={<EditUserComplete />} />
                    <Route path="/user/myPage" element={<MyPage />} />
                    <Route path="/user/signUpComplete" element={<SignUpComplete />} />
                    <Route path="/user/FindId" element={<FindId />} />

                    // Map
                    <Route path='/home' element = {<Home />} />
                    <Route path="/city/:code" element = {<City />} />
                    <Route path="/city/:city_code/:gu_code" element = {<Gu />} />

                    // Board
                    <Route path = '/city/:city_code/:gu_code/spot' element = {<ListBoard/>}/>
                    <Route path = '/city/:city_code/:gu_code/spot/:image_id' element = {<DetailBoard/>}/>
                    <Route path = '/city/:city_code/:gu_code/spot/upload' element = {<UploadBoard/>}/>


                </Routes>
            </div>
        </BrowserRouter>
    )
}

export default App;