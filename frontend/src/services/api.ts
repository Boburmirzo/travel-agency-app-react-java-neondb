import axios from 'axios';
import {
  TravelUser,
  TravelDestination,
  TravelBooking,
  TravelReview,
  CreateUserForm,
  UpdateUserForm,
  CreateDestinationForm,
  UpdateDestinationForm,
  CreateBookingForm,
  UpdateBookingForm,
  CreateReviewForm,
  UpdateReviewForm,
} from '../types';

const LOCAL_API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';

// Create axios instance with local backend configuration
const api = axios.create({
  baseURL: LOCAL_API_BASE_URL,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor for logging
api.interceptors.request.use(
  (config) => {
    console.log(`Backend API Request: ${config.method?.toUpperCase()} ${config.url}`);
    return config;
  },
  (error) => {
    console.error('Backend API Request Error:', error);
    return Promise.reject(error);
  }
);

// Response interceptor for error handling
api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    console.error('Backend API Response Error:', error.response?.data || error.message);
    throw error;
  }
);

// User API
export const userApi = {
  getAll: async (): Promise<TravelUser[]> => {
    const response = await api.get<TravelUser[]>('/users');
    return response.data;
  },

  getById: async (id: number): Promise<TravelUser> => {
    const response = await api.get<TravelUser>(`/users/${id}`);
    return response.data;
  },

  create: async (userData: CreateUserForm): Promise<TravelUser> => {
    const response = await api.post<TravelUser>('/users', userData);
    return response.data;
  },

  update: async (id: number, userData: UpdateUserForm): Promise<TravelUser> => {
    const response = await api.put<TravelUser>(`/users/${id}`, userData);
    return response.data;
  },

  delete: async (id: number): Promise<void> => {
    await api.delete(`/users/${id}`);
  },
};

// Destination API
export const destinationApi = {
  getAll: async (): Promise<TravelDestination[]> => {
    const response = await api.get<TravelDestination[]>('/destinations');
    return response.data;
  },

  getById: async (id: number): Promise<TravelDestination> => {
    const response = await api.get<TravelDestination>(`/destinations/${id}`);
    return response.data;
  },

  create: async (destinationData: CreateDestinationForm): Promise<TravelDestination> => {
    const response = await api.post<TravelDestination>('/destinations', destinationData);
    return response.data;
  },

  update: async (id: number, destinationData: UpdateDestinationForm): Promise<TravelDestination> => {
    const response = await api.put<TravelDestination>(`/destinations/${id}`, destinationData);
    return response.data;
  },

  delete: async (id: number): Promise<void> => {
    await api.delete(`/destinations/${id}`);
  },
};

// Booking API
export const bookingApi = {
  getAll: async (): Promise<TravelBooking[]> => {
    const response = await api.get<TravelBooking[]>('/bookings');
    return response.data;
  },

  getById: async (id: number): Promise<TravelBooking> => {
    const response = await api.get<TravelBooking>(`/bookings/${id}`);
    return response.data;
  },

  getByUserId: async (userId: number): Promise<TravelBooking[]> => {
    const response = await api.get<TravelBooking[]>(`/bookings/user/${userId}`);
    return response.data;
  },

  create: async (bookingData: CreateBookingForm): Promise<TravelBooking> => {
    const response = await api.post<TravelBooking>('/bookings', bookingData);
    return response.data;
  },

  update: async (id: number, bookingData: UpdateBookingForm): Promise<TravelBooking> => {
    const response = await api.put<TravelBooking>(`/bookings/${id}`, bookingData);
    return response.data;
  },

  delete: async (id: number): Promise<void> => {
    await api.delete(`/bookings/${id}`);
  },
};

// Review API
export const reviewApi = {
  getAll: async (): Promise<TravelReview[]> => {
    const response = await api.get<TravelReview[]>('/reviews');
    return response.data;
  },

  getByDestinationId: async (destinationId: number): Promise<TravelReview[]> => {
    const response = await api.get<TravelReview[]>(`/reviews/destination/${destinationId}`);
    return response.data;
  },

  create: async (reviewData: CreateReviewForm): Promise<TravelReview> => {
    const response = await api.post<TravelReview>('/reviews', reviewData);
    return response.data;
  },

  update: async (id: number, reviewData: UpdateReviewForm): Promise<TravelReview> => {
    const response = await api.put<TravelReview>(`/reviews/${id}`, reviewData);
    return response.data;
  },

  delete: async (id: number): Promise<void> => {
    await api.delete(`/reviews/${id}`);
  },
};

// Health check API
export const healthApi = {
  check: async () => {
    try {
      const response = await api.get('/health');
      return { status: 'ok', data: response.data };
    } catch (error) {
      return { status: 'error', error: error instanceof Error ? error.message : 'Unknown error' };
    }
  },
};
