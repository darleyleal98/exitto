import { createNativeStackNavigator } from "@react-navigation/native-stack";
import StartScreen from "./presentation/screens/start";
import HomeScreen from "./presentation/screens/home";
import { useEffect, useState } from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { ActivityIndicator, View } from "react-native";

const Stack = createNativeStackNavigator();

export default function Index() {
  const [isNotFirstAccess, setIsNotFirstAccess] = useState(false);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const verify = async () => {
      try {
        const accessed = await AsyncStorage.getItem('access');
        const parsedUser = accessed ? JSON.parse(accessed) : null;

        if (parsedUser?.accessed) {
          setIsNotFirstAccess(true)
        }
      } catch (error) {
        console.log('Error:', error)
      } finally {
        setIsLoading(false);
      }
    }

    verify();
  }, []);

  if (isLoading) {
    return (
      <View style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
        <ActivityIndicator size="large" />
      </View>
    );
  }

  return (
    <Stack.Navigator initialRouteName={isNotFirstAccess ? "Home" : "Start"} screenOptions={{ headerShown: false }}>
      <Stack.Screen name="Start" component={StartScreen} />
      <Stack.Screen name="Home" component={HomeScreen} />
    </Stack.Navigator>
  )
}