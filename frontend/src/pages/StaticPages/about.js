// material-ui
import { Typography } from '@mui/material';

// project import
import MainCard from 'components/MainCard';

// ==============================|| SAMPLE PAGE ||============================== //
import React from 'react';

const SamplePage = () => {
  return (<MainCard title="About">
    <Typography variant="body2">
        This is the about page of the application. Here you can find information about the app,
         its features, and how to use it.
    </Typography>
  </MainCard>
)};

export default SamplePage;
