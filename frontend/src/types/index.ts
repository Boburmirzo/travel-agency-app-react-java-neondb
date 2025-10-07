// Shared types for the Travel Agency frontend application
export interface TravelUser {
  id: number;
  uuid: string;
  firstName: string;
  lastName: string;
  email: string;
  dateCreated: string;
  dateUpdated?: string;
}

export interface TravelDestination {
  id: number;
  uuid: string;
  name: string;
  description: string;
  price: number;
  rating: number;
  dateCreated: string;
  dateUpdated?: string;
}

export interface TravelBooking {
  id: number;
  uuid: string;
  userId: number;
  destinationId: number;
  bookingDate: string;
  date: string;
  status: 'pending' | 'confirmed' | 'cancelled';
  numberOfPeople: number;
  dateCreated: string;
  dateUpdated?: string;
  // Joined data
  firstName?: string;
  lastName?: string;
  email?: string;
  destinationName?: string;
  destinationPrice?: number;
}

export interface TravelReview {
  id: number;
  uuid: string;
  userId: number;
  destinationId: number;
  rating: number;
  comment?: string;
  reviewDate: string;
  dateCreated: string;
  dateUpdated?: string;
  // Joined data
  firstName?: string;
  lastName?: string;
  destinationName?: string;
}

// Form types
export interface CreateUserForm {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
}

export interface UpdateUserForm {
  firstName?: string;
  lastName?: string;
  email?: string;
  password?: string;
}

export interface CreateDestinationForm {
  name: string;
  description: string;
  price: number;
}

export interface UpdateDestinationForm {
  name?: string;
  description?: string;
  price?: number;
}

export interface CreateBookingForm {
  userId: number;
  destinationId: number;
  date: string;
  numberOfPeople: number;
}

export interface UpdateBookingForm {
  date?: string;
  status?: 'pending' | 'confirmed' | 'cancelled';
  numberOfPeople?: number;
}

export interface CreateReviewForm {
  userId: number;
  destinationId: number;
  rating: number;
  comment?: string;
}

export interface UpdateReviewForm {
  rating?: number;
  comment?: string;
}

// API Response types
export interface ApiResponse<T> {
  success: boolean;
  data?: T;
  error?: string;
  message?: string;
}

export interface PaginatedResponse<T> {
  data: T[];
  total: number;
  page: number;
  pageSize: number;
}

// UI state types
export interface LoadingState {
  isLoading: boolean;
  error: string | null;
}

export interface FormState<T> extends LoadingState {
  data: T;
}
