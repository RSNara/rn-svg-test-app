import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { StyleSheet, Text, View, Button, TurboModuleRegistry } from 'react-native';

const MyNativeModule = TurboModuleRegistry.get('MyNativeModule');

export default function App() {
  return (
    <View style={styles.container}>
      <Button onPress={() => {
        if (!MyNativeModule) {
          console.warn('MyNativeModule is null');
        }

        if (MyNativeModule) {
          MyNativeModule.toast('My toast!');
        }
      }} title="Press Me"/>
      <Text>Open up App.js to start working on your app!</Text>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
