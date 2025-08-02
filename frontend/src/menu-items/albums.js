// assets
import { PictureOutlined, FileImageOutlined } from '@ant-design/icons';

// icons
const icons = {
  PictureOutlined,
  FileImageOutlined
};

// ==============================|| MENU ITEMS - SAMPLE PAGE & DOCUMENTATION ||============================== //

const albums = {
  id: 'albums',
  title: 'Albums',
  type: 'group',
  children: [
    {
      id: 'Albums',
      title: 'Albums',
      type: 'item',
      url: '/login',
      icon: icons.PictureOutlined,

    },
    {
      id: ' Add Album',
      title: 'Add Albums',
      type: 'item',
      url: '/album/add',
      icon: icons.FileImageOutlined,

    }
  ]
};

export default albums;
