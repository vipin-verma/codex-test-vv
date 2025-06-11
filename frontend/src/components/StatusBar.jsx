export default function StatusBar({ game }) {
  const { status, nextTurn } = game
  let text = ''
  if (status === 'IN_PROGRESS') {
    text = `Next: ${nextTurn}`
  } else if (status === 'DRAW') {
    text = 'Draw'
  } else {
    text = `Winner: ${status.startsWith('X') ? 'X' : 'O'}`
  }
  return <div className='status'>{text}</div>
}
