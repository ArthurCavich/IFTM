import './Botao.css';

export default function Botao({ texto, onClick }) {
    return (
        <button className="btn" onClick={onClick}>
            {texto}
        </button>
    );
}
