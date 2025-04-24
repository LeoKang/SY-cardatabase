import { AppBar, CssBaseline, Toolbar, Typography } from '@mui/material';
import Container from '@mui/material/Container';

function App() {
  return (
    <Container maxWidth="xl">
      <CssBaseline />
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6">
            Car Shop
          </Typography>
        </Toolbar>
      </AppBar>
    </Container>
  )
}

export default App
