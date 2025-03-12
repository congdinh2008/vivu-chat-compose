import api from './api';
import { JwtResponse, LoginRequest, LogoutRequest } from '../types/auth';

const authService = {
  login: async (username: string, password: string): Promise<JwtResponse> => {
    const loginRequest: LoginRequest = { username, password };
    const response = await api.post<JwtResponse>('/api/auth/login', loginRequest);
    return response.data;
  },
  
  logout: async (username: string): Promise<void> => {
    const logoutRequest: LogoutRequest = { username };
    await api.post('/api/auth/logout', logoutRequest);
  },
  
  refreshToken: async (token: string): Promise<JwtResponse> => {
    const response = await api.post<JwtResponse>('/api/auth/refresh', { refreshToken: token });
    return response.data;
  }
};

export default authService;
