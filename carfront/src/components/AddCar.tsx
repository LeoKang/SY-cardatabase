import {useState} from 'react';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import { Car } from '../types';
import { DialogActions, Button } from '@mui/material';
import {useMutation, useQueryClient} from '@tanstack/react-query';
import {addCar} from '../api/carapi';
import CarDialogContent from './CarDialogContent';

function AddCar() {
    const [open, setOpen] = useState(false);
    const [car, setCar] = useState<Car>({
        brand:'',
        model:'',
        color:'',
        registrationNumber:'',
        modelYear:0,
        price:0
    });

    const handleClickOpen = () => {
        setOpen(true);
    }

    const handleClose = () => {
        setOpen(false);
    }

    const handleChange = (event : React.ChangeEvent<HTMLInputElement>) =>
    {
        setCar({...car, [event.target.name]:event.target.value});
    }

    const queryClient = useQueryClient();

    const {mutate } = useMutation(addCar, {
        onSuccess: () => {
            queryClient.invalidateQueries(["cars"]);
        },
        onError: (err) => {
            console.error(err);
        }
    })

    const handleSave = () => {
        mutate(car);
        setCar({brand:'', model:'', color:'', registrationNumber:'', modelYear:0, price:0});
        handleClose();
    }

    return (
        <div>
            <Button onClick={handleClickOpen}>New Car</Button>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>New Car</DialogTitle>
                <CarDialogContent car={car} handleChange={handleChange}/>
                <DialogActions>
                    <button onClick={handleClose}>Cancel</button>
                    <button onClick={handleSave}>Save</button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

export default AddCar;