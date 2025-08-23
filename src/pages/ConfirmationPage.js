import React, { useEffect, useState } from 'react';

const ConfirmationPage = ({ 
  orderId, 
  customerInfo,
  orderItems,
  total,
  onNewOrder 
}) => {
  const [timeRemaining, setTimeRemaining] = useState(35); // 35 minutes
  const [orderStatus, setOrderStatus] = useState('confirmed'); // confirmed, preparing, delivering, delivered

  useEffect(() => {
    // Simulation du statut de commande
    const statusTimer = setTimeout(() => setOrderStatus('preparing'), 2000);
    const preparingTimer = setTimeout(() => setOrderStatus('delivering'), 15000);

    return () => {
      clearTimeout(statusTimer);
      clearTimeout(preparingTimer);
    };
  }, []);

  useEffect(() => {
    // Décompte du temps
    if (timeRemaining > 0) {
      const timer = setTimeout(() => {
        setTimeRemaining(prev => prev - 1);
      }, 60000); // Chaque minute

      return () => clearTimeout(timer);
    }
  }, [timeRemaining]);

  const getStatusInfo = (status) => {
    switch (status) {
      case 'confirmed':
        return {
          icon: '✅',
          title: 'Commande confirmée',
          description: 'Votre commande a été reçue et confirmée',
          color: 'text-green-600',
          bgColor: 'bg-green-100',
          borderColor: 'border-green-300'
        };
      case 'preparing':
        return {
          icon: '👨‍🍳',
          title: 'En préparation',
          description: 'Nos chefs préparent vos délicieuses pizzas',
          color: 'text-blue-600',
          bgColor: 'bg-blue-100',
          borderColor: 'border-blue-300'
        };
      case 'delivering':
        return {
          icon: '🚗',
          title: 'En livraison',
          description: 'Votre commande est en route vers vous',
          color: 'text-orange-600',
          bgColor: 'bg-orange-100',
          borderColor: 'border-orange-300'
        };
      case 'delivered':
        return {
          icon: '🎉',
          title: 'Livré !',
          description: 'Bon appétit !',
          color: 'text-green-600',
          bgColor: 'bg-green-100',
          borderColor: 'border-green-300'
        };
      default:
        return getStatusInfo('confirmed');
    }
  };

  const statusInfo = getStatusInfo(orderStatus);

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="max-w-3xl mx-auto">
        {/* En-tête de confirmation */}
        <div className={`${statusInfo.bgColor} ${statusInfo.borderColor} border rounded-2xl p-8 text-center mb-8`}>
          <div className="text-6xl mb-4">{statusInfo.icon}</div>
          <h1 className={`text-4xl font-bold ${statusInfo.color} mb-4`}>
            {statusInfo.title}
          </h1>
          <p className="text-xl text-gray-700 mb-6">
            {statusInfo.description}
          </p>
          <div className="bg-white rounded-lg p-4 inline-block">
            <p className="text-2xl font-bold text-gray-800">
              Commande #{orderId}
            </p>
          </div>
        </div>

        {/* Temps de livraison estimé */}
        {orderStatus !== 'delivered' && (
          <div className="bg-white rounded-xl shadow-lg p-6 mb-8">
            <div className="flex items-center justify-between">
              <div>
                <h3 className="text-xl font-semibold mb-2">Temps de livraison estimé</h3>
                <p className="text-gray-600">
                  Votre commande devrait arriver dans environ
                </p>
              </div>
              <div className="text-right">
                <div className="text-3xl font-bold text-red-600">
                  {timeRemaining} min
                </div>
                <div className="text-sm text-gray-500">
                  ({new Date(Date.now() + timeRemaining * 60000).toLocaleTimeString([], {
                    hour: '2-digit',
                    minute: '2-digit'
                  })})
                </div>
              </div>
            </div>
          </div>
        )}

        {/* Barre de progression */}
        <div className="bg-white rounded-xl shadow-lg p-6 mb-8">
          <h3 className="text-xl font-semibold mb-6">Suivi de votre commande</h3>
          
          <div className="flex items-center justify-between relative">
            {/* Ligne de progression */}
            <div className="absolute top-4 left-4 right-4 h-1 bg-gray-200 rounded-full">
              <div 
                className="h-full bg-red-500 rounded-full transition-all duration-1000"
                style={{ 
                  width: orderStatus === 'confirmed' ? '25%' : 
                         orderStatus === 'preparing' ? '50%' : 
                         orderStatus === 'delivering' ? '75%' : '100%' 
                }}
              />
            </div>
            
            {/* Étapes */}
            {['confirmed', 'preparing', 'delivering', 'delivered'].map((status, index) => {
              const isActive = ['confirmed', 'preparing', 'delivering', 'delivered'].indexOf(orderStatus) >= index;
              const isCurrent = orderStatus === status;
              
              return (
                <div key={status} className="flex flex-col items-center relative z-10">
                  <div className={`w-8 h-8 rounded-full flex items-center justify-center text-sm font-bold transition-all duration-300 ${
                    isActive 
                      ? 'bg-red-500 text-white' 
                      : 'bg-gray-200 text-gray-500'
                  } ${isCurrent ? 'ring-4 ring-red-200 scale-110' : ''}`}>
                    {index + 1}
                  </div>
                  <div className={`mt-2 text-center transition-colors duration-300 ${
                    isActive ? 'text-red-600' : 'text-gray-400'
                  }`}>
                    <div className="text-xs font-medium">
                      {status === 'confirmed' && 'Confirmée'}
                      {status === 'preparing' && 'Préparation'}
                      {status === 'delivering' && 'Livraison'}
                      {status === 'delivered' && 'Livrée'}
                    </div>
                  </div>
                </div>
              );
            })}
          </div>
        </div>

        {/* Détails de la commande */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-8">
          {/* Informations client */}
          <div className="bg-white rounded-xl shadow-lg p-6">
            <h3 className="text-xl font-semibold mb-4 flex items-center">
              <span className="mr-2">👤</span>
              Informations de livraison
            </h3>
            <div className="space-y-3 text-gray-600">
              <p><strong>Nom :</strong> {customerInfo?.nom}</p>
              <p><strong>Email :</strong> {customerInfo?.email}</p>
              <p><strong>Téléphone :</strong> {customerInfo?.telephone}</p>
              <p><strong>Adresse :</strong> {customerInfo?.adresse}</p>
            </div>
          </div>

          {/* Récapitulatif de commande */}
          <div className="bg-white rounded-xl shadow-lg p-6">
            <h3 className="text-xl font-semibold mb-4 flex items-center">
              <span className="mr-2">🧾</span>
              Récapitulatif
            </h3>
            <div className="space-y-2 text-gray-600 mb-4">
              {orderItems?.map(item => (
                <div key={item.id} className="flex justify-between">
                  <span>{item.quantite}x {item.nom}</span>
                  <span>{(item.prix * item.quantite).toFixed(2)}€</span>
                </div>
              ))}
            </div>
            <div className="border-t pt-2">
              <div className="flex justify-between font-bold text-lg">
                <span>Total</span>
                <span className="text-red-600">{total?.toFixed(2)}€</span>
              </div>
            </div>
          </div>
        </div>

        {/* Informations utiles */}
        <div className="bg-gradient-to-r from-blue-50 to-purple-50 rounded-xl p-6 mb-8">
          <h3 className="text-xl font-semibold mb-4 flex items-center">
            <span className="mr-2">💡</span>
            Informations utiles
          </h3>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4 text-sm text-gray-600">
            <div className="flex items-start">
              <span className="mr-2">📧</span>
              <div>
                <p className="font-medium">Email de confirmation</p>
                <p>Vous recevrez un email avec tous les détails</p>
              </div>
            </div>
            <div className="flex items-start">
              <span className="mr-2">📞</span>
              <div>
                <p className="font-medium">Besoin d'aide ?</p>
                <p>Contactez-nous au 01 23 45 67 89</p>
              </div>
            </div>
            <div className="flex items-start">
              <span className="mr-2">🚗</span>
              <div>
                <p className="font-medium">Problème de livraison ?</p>
                <p>Notre livreur vous contactera si besoin</p>
              </div>
            </div>
            <div className="flex items-start">
              <span className="mr-2">⭐</span>
              <div>
                <p className="font-medium">Votre avis compte</p>
                <p>N'hésitez pas à nous laisser un commentaire</p>
              </div>
            </div>
          </div>
        </div>

        {/* Actions */}
        <div className="text-center space-y-4">
          <button
            onClick={onNewOrder}
            className="bg-red-500 text-white px-8 py-4 rounded-xl text-lg hover:bg-red-600 transform hover:scale-105 transition-all duration-200 shadow-lg"
          >
            <span className="mr-2">🍕</span>
            Commander à nouveau
          </button>
          
          <div className="text-gray-500 text-sm">
            <p>Merci de votre confiance ! 🙏</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ConfirmationPage;