import React from 'react';

const LoadingSpinner = ({ message = 'Chargement...', size = 'medium' }) => {
  const sizeClasses = {
    small: 'w-6 h-6',
    medium: 'w-12 h-12',
    large: 'w-16 h-16'
  };

  const PizzaSpinner = () => (
    <div className="relative">
      <div className={`${sizeClasses[size]} animate-spin`}>
        <div className="text-4xl animate-pulse">🍕</div>
      </div>
    </div>
  );

  return (
    <div className="flex flex-col items-center justify-center min-h-[200px] p-8">
      <PizzaSpinner />
      
      {message && (
        <p className="mt-4 text-gray-600 text-lg font-medium animate-pulse">
          {message}
        </p>
      )}
      
      {/* Points animés */}
      <div className="flex space-x-1 mt-2">
        {[0, 1, 2].map((i) => (
          <div
            key={i}
            className="w-2 h-2 bg-red-500 rounded-full animate-bounce"
            style={{
              animationDelay: `${i * 0.1}s`
            }}
          ></div>
        ))}
      </div>
    </div>
  );
};

// Variante inline pour les boutons
export const InlineSpinner = ({ className = "w-4 h-4" }) => (
  <div className={`${className} animate-spin rounded-full border-2 border-white border-t-transparent`}></div>
);

export default LoadingSpinner;