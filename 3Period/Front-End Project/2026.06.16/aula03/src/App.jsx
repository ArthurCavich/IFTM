import Home from "./Home";
import Contatos from "./Contatos";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Menu from "./Menu";

export default function App() {
  return (
    <BrowserRouter>
      <Menu />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/contatos" element={<Contatos />} />
      </Routes>
    </BrowserRouter>
  )
}