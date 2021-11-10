import React from 'react';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import { red } from '@mui/material/colors';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ShareIcon from '@mui/icons-material/Share';

const ContactCard = (props) => {
    const { contact } = props;
  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            {contact.name.substring(0,1)}
          </Avatar>
        }
        title={contact.name}
        subheader={contact.phone}
      />
      <CardContent>
        <Typography variant="body2" color="text.secondary">
          {contact.email}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton aria-label="edit">
          <FavoriteIcon />
        </IconButton>
        <IconButton aria-label="delete">
          <ShareIcon />
        </IconButton>
      </CardActions>
    </Card>
  );
};

export default ContactCard;
