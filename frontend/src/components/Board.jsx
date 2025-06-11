import { useEffect, useState } from 'react'
import api from '../api'
import Square from './Square'
import StatusBar from './StatusBar'
import NewGameButton from './NewGameButton'

export default function Board() {
  const [game, setGame] = useState(null)

  useEffect(() => {
    startGame()
  }, [])

  function startGame() {
    api.post('/games').then(res => {
      const id = res.data.gameId
      api.get(`/games/${id}`).then(r => setGame(r.data))
    })
  }

  function handleClick(index) {
    if (!game || game.status !== 'IN_PROGRESS') return
    api.post(`/games/${game.id}/move`, { index, player: game.nextTurn })
      .then(res => setGame(res.data))
  }

  if (!game) return <div>Loading...</div>

  return (
    <div className='board'>
      <StatusBar game={game} />
      <div className='grid'>
        {game.board.map((v, i) => (
          <Square key={i} value={v} onClick={() => handleClick(i)} />
        ))}
      </div>
      <NewGameButton onClick={startGame} />
    </div>
  )
}
