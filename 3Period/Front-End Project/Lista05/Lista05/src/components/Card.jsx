import './Card.css';

export default function Card({ titulo }) {
  return (
    <div className="card">
      <h3>{titulo}</h3>
    </div>
  );
}