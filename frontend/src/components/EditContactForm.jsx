import {
  Button,
  Card,
  CardContent,
  CardHeader,
  Dialog,
  DialogActions,
  DialogContent,
  Divider,
  Grid,
  TextField,
} from '@mui/material';
import React, { useState } from 'react';

import api from '../api/apiConfig';

const EditContactForm = ( props ) => {
  const { openForm, handleFormClose, contact } = props;
  const [values, setValues] = useState(contact);

  const updateValue = (event) => {
    setValues({
      ...values,
      [event.target.name]: event.target.value,
    });
  };

  const submitForm = async (event) => {
    event.preventDefault();
    try {
      await api.put(`/person/update/${contact.id}`, values);
      handleFormClose();
    } catch (error) {
      alert('error', `${error}`)
    }
  }
  
  return (
    <Dialog open={openForm} onClose={handleFormClose} maxWidth="xs">
      <DialogContent style={{ padding: 0 }}>
        <Card>
          <form autoComplete="off" onSubmit={submitForm}>
          <CardHeader title="Add Contact" />
          <Divider />
          <CardContent>
            <Grid container spacing={3}>
              <Grid item md={12} xs={12}>
                <TextField
                  fullWidth
                  label="Nome"
                  name="name"
                  onChange={updateValue}
                  required
                  value={values.name}
                  variant="outlined"
                />
              </Grid>
              <Grid item md={12} xs={12}>
                <TextField
                  fullWidth
                  label="Email"
                  name="email"
                  type="email"
                  onChange={updateValue}
                  required
                  value={values.email}
                  variant="outlined"
                />
              </Grid>
              <Grid item md={12} xs={12}>
                <TextField
                  fullWidth
                  label="Telephone"
                  name="phone"
                  type="tel"
                  onChange={updateValue}
                  required
                  value={values.phone}
                  variant="outlined"
                />
              </Grid>
            </Grid>
          </CardContent>
          <Divider />
          <DialogActions>
            <Button
              variant="outlined"
              fullWidth
              onClick={handleFormClose}
              size="large"
            >
              CANCEL
            </Button>
            <Button
              fullWidth
              color="primary"
              variant="contained"
              size="large"
              type="submit"
              name="save-button"
            >
              SAVE CONTACT
            </Button>
          </DialogActions>
        </form>
      </Card>
      </DialogContent>
    </Dialog>
  );
};

export default EditContactForm;
