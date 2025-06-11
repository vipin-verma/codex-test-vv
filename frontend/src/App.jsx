import { Routes, Route } from 'react-router-dom'
import Board from './components/Board'

export default function App() {
  return (
    <Routes>
      <Route path='/' element={<Board />} />
    </Routes>
  )
}
